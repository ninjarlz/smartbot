package pl.tul.smartbot.mapper.chat;

import org.mapstruct.Mapper;
import pl.tul.smartbot.model.dto.chat.ChatDTO;
import pl.tul.smartbot.model.entity.chat.ChatEntity;

@Mapper
public interface ChatMapper {
    ChatEntity DTOtoEntity(ChatDTO chatDTO);
    ChatDTO entityToDTO(ChatEntity chatEntity);
}
