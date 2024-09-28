package pl.tul.smartbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.smartbot.model.entity.message.MessageEntity;

/**
 * Interface describing public operations for message persistence.
 */
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
