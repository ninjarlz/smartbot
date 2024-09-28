package pl.tul.smartbot.controller.rest.message;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.tul.smartbot.exception.chat.ChatNotFoundException;
import pl.tul.smartbot.mapper.message.MessageMapper;
import pl.tul.smartbot.model.dto.message.MessageDTO;
import pl.tul.smartbot.model.request.message.MessageRequestV1;
import pl.tul.smartbot.model.response.message.MessageResponseV1;
import pl.tul.smartbot.service.message.MessageService;

import static pl.tul.smartbot.util.constant.rest.ApiUrls.CHAT_ENDPOINT_V1;
import static pl.tul.smartbot.util.constant.rest.ApiUrls.MESSAGE_PATH;
import static pl.tul.smartbot.util.constant.security.Permissions.READ_WRITE_CHATBOT_PERMISSION_EXPRESSION;

@RestController
@RequestMapping(CHAT_ENDPOINT_V1)
@RequiredArgsConstructor
@Validated
public class MessageControllerV1 {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    @PostMapping(value = "/{chatId}" + MESSAGE_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(READ_WRITE_CHATBOT_PERMISSION_EXPRESSION)
    public ResponseEntity<MessageResponseV1> createMessage(@PathVariable Long chatId, @RequestBody @Valid MessageRequestV1 messageRequestV1) {
        try {
            messageService.createMessage(chatId, messageRequestV1);
        } catch (ChatNotFoundException e) {
            throw new ResponseStatusException();
        }
    }
}
