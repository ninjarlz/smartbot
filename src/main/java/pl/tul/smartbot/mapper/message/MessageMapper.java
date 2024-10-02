package pl.tul.smartbot.mapper.message;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pl.tul.smartbot.model.dto.message.MessageDTO;
import pl.tul.smartbot.model.entity.message.AuthorType;
import pl.tul.smartbot.model.entity.message.MessageEntity;
import pl.tul.smartbot.model.request.message.MessageRequestV1;
import pl.tul.smartbot.model.response.message.MessageResponseV1;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class MessageMapper {

    public MessageEntity DTOtoEntity(AuthorType authorType, MessageDTO messageDTO) {
        return MessageEntity.builder()
                .content(messageDTO.getContent())
                .chatId(messageDTO.getChatId())
                .timestamp(messageDTO.getTimestamp())
                .authorType(authorType)
                .build();
    }

    public abstract MessageDTO entityToDTO(MessageEntity messageEntity);

    public MessageDTO requestToDTO(long chatId, MessageRequestV1 messageRequest) {
        return MessageDTO.builder()
                .chatId(chatId)
                .content(messageRequest.getContent())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public abstract MessageResponseV1 DTOtoResponse(MessageDTO messageDTO);
}
