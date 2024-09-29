package pl.tul.smartbot.service.message;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface MessageAiAssistant {

    @UserMessage("{{message}}")
    @SystemMessage("Answer the message using data fetched from dynamic URLs")
    String processMessage(@V("messageContent") String messageContent);

}
