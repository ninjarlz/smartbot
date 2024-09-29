package pl.tul.smartbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.smartbot.model.entity.chat.ChatEntity;

/**
 * Interface describing public operations for chats persistence.
 */
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
}
