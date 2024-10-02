package pl.tul.smartbot.mapper.chat;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pl.tul.smartbot.model.dto.chat.ChatDTO;
import pl.tul.smartbot.model.entity.chat.ChatEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChatMapper {
    ChatEntity DTOtoEntity(ChatDTO chatDTO);
    ChatDTO entityToDTO(ChatEntity chatEntity);
}
