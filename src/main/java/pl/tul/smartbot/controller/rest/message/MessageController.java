package pl.tul.smartbot.controller.rest.message;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.tul.smartbot.model.dto.message.ChatMessageDTO;
import pl.tul.smartbot.service.message.MessageAiAssistant;

import static pl.tul.smartbot.util.constant.rest.ApiUrls.MESSAGE_URL;
import static pl.tul.smartbot.util.constant.security.Permissions.READ_WRITE_CHATBOT_PERMISSION_EXPRESSION;

@RestController
@RequestMapping(MESSAGE_URL)
@RequiredArgsConstructor
public class MessageController {

    private final MessageAiAssistant messageAiAssistant;

    @PostMapping
    @PreAuthorize(READ_WRITE_CHATBOT_PERMISSION_EXPRESSION)
    public ChatMessageDTO processMessage(@RequestParam("content") String messageContent) {
        return messageAiAssistant.processMessage(messageContent);
    }
}
