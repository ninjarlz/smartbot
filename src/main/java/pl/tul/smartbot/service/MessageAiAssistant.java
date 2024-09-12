package pl.tul.smartbot.service;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import pl.tul.smartbot.model.ChatMessageDTO;

@AiService
public interface MessageAiAssistant {

    @UserMessage("{{message}}")
    ChatMessageDTO processMessage(@V("message") String message);

}
