package pl.tul.smartbot.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.tul.smartbot.model.ChatMessageDTO;
import pl.tul.smartbot.service.MessageAiAssistant;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final String MESSAGE_URL = "/message";

    private final MessageAiAssistant messageAiAssistant;

    @PostMapping(MESSAGE_URL)
    public ChatMessageDTO processMessage(@RequestParam("content") String messageContent) {
        return messageAiAssistant.processMessage(messageContent);
    }
}
