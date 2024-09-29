package pl.tul.smartbot.service.message;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tul.smartbot.exception.chat.ChatNotFoundException;
import pl.tul.smartbot.exception.security.AuthenticationException;
import pl.tul.smartbot.mapper.message.MessageMapper;
import pl.tul.smartbot.model.dto.chat.ChatDTO;
import pl.tul.smartbot.model.dto.message.MessageDTO;
import pl.tul.smartbot.model.entity.message.AuthorType;
import pl.tul.smartbot.model.entity.message.MessageEntity;
import pl.tul.smartbot.repository.MessageRepository;
import pl.tul.smartbot.service.chat.ChatService;
import pl.tul.smartbot.util.security.AuthenticationDelegate;

import static graphql.com.google.common.base.Objects.equal;
import static pl.tul.smartbot.util.constant.security.Permissions.WRITE_CHATBOT_PERMISSION;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final AuthenticationDelegate authenticationDelegate;
    private final ChatService chatService;
    private final MessageRepository messageRepository;
    private final MessageAiAssistant messageAiAssistant;
    private final MessageMapper messageMapper;

    @Transactional(rollbackOn = Exception.class)
    public MessageDTO sendMessage(MessageDTO message) throws ChatNotFoundException, AuthenticationException {
        ChatDTO chat = chatService.getChat(message.getChatId());
        AuthorType authorType = getAuthorType();
        if (authorType == AuthorType.USER) {
            throwIfChatNotBelongToUser(chat);
        }
        saveMessage(authorType, message);
        String responseMessageContent = messageAiAssistant.processMessage(message.getContent());
        MessageDTO responseMessage = MessageDTO.builder()
                .content(responseMessageContent)
                .chatId(chat.getId())
                .timestamp(System.currentTimeMillis())
                .build();
        return saveMessage(AuthorType.AI, responseMessage);
    }

    private MessageDTO saveMessage(AuthorType authorType, MessageDTO message) {
        MessageEntity entity = messageMapper.DTOtoEntity(authorType, message);
        entity = messageRepository.save(entity);
        return messageMapper.entityToDTO(entity);
    }

    private AuthorType getAuthorType() throws AuthenticationException {
        return authenticationDelegate.hasTokenPermissions(WRITE_CHATBOT_PERMISSION) ?
            AuthorType.ADMIN : AuthorType.USER;
    }

    private void throwIfChatNotBelongToUser(ChatDTO chat) throws AuthenticationException, ChatNotFoundException {
        long userId = authenticationDelegate.getUserId();
        if (equal(chat.getUserId(), userId)) {
            return;
        }
        throw new ChatNotFoundException(chat.getId());
    }
}
