package pl.tul.smartbot.service.message;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tul.smartbot.exception.chat.ChatNotFoundException;
import pl.tul.smartbot.model.dto.chat.ChatDTO;
import pl.tul.smartbot.model.dto.message.MessageDTO;
import pl.tul.smartbot.model.entity.chat.ChatEntity;
import pl.tul.smartbot.model.entity.message.AuthorType;
import pl.tul.smartbot.model.entity.message.MessageEntity;
import pl.tul.smartbot.model.request.message.MessageRequestV1;
import pl.tul.smartbot.model.response.message.MessageResponseV1;
import pl.tul.smartbot.repository.MessageRepository;
import pl.tul.smartbot.service.chat.ChatService;
import pl.tul.smartbot.util.security.AuthenticationDelegate;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final AuthenticationDelegate authenticationDelegate;
    private final ChatService chatService;
    private final MessageRepository messageRepository;
    private final MessageAiAssistant messageAiAssistant;

    @Transactional(rollbackOn = Exception.class)
    public MessageDTO createMessage(MessageDTO messageDTO) throws ChatNotFoundException {
        ChatDTO chat = chatService.getChat(messageDTO.getChatId());
        return MessageDTO.builder().build();
        return MessageResponseV1.builder().build();
    }

    private MessageEntity buildMessageEntityFromRequest(long chatId, MessageRequestV1 messageRequestV1) {
        return MessageEntity.builder()
                .content(messageRequestV1.getContent())
                .authorType(AuthorType.USER)
                .chatId(chatId)
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
