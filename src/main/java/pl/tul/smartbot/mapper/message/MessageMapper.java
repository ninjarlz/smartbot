package pl.tul.smartbot.mapper.message;

import org.mapstruct.Mapper;
import pl.tul.smartbot.model.dto.message.MessageDTO;
import pl.tul.smartbot.model.entity.message.MessageEntity;
import pl.tul.smartbot.model.request.message.MessageRequestV1;
import pl.tul.smartbot.model.response.message.MessageResponseV1;

@Mapper
public interface MessageMapper {
    MessageEntity DTOtoEntity(MessageDTO messageDTO);
    MessageDTO entityToDTO(MessageDTO messageDTO);
    MessageResponseV1 DTOtoResponse(MessageResponseV1 messageResponse);
    MessageDTO requestToDTO(MessageRequestV1 messageRequest);
}
