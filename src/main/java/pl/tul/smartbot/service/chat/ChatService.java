package pl.tul.smartbot.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tul.smartbot.exception.chat.ChatNotFoundException;
import pl.tul.smartbot.mapper.chat.ChatMapper;
import pl.tul.smartbot.model.dto.chat.ChatDTO;
import pl.tul.smartbot.model.entity.chat.ChatEntity;
import pl.tul.smartbot.repository.ChatRepository;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;

    public ChatDTO getChat(long chatId) throws ChatNotFoundException {
        ChatEntity entity = chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(chatId));
        return chatMapper.entityToDTO(entity);
    }

}
