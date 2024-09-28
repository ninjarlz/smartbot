package pl.tul.smartbot.model.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tul.smartbot.model.entity.chat.LlmType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChatDTO {

    private Long id;

    private Long userId;

    private LlmType llmType;

    private Long createdAt;

    private Long lastMessageTimestamp;

    private Long lastMessageId;
}
