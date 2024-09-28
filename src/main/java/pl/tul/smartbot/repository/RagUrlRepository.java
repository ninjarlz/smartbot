package pl.tul.smartbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.smartbot.model.entity.url.RagUrlEntity;

/**
 * Interface describing public operations for RAG URLs persistence.
 */
public interface RagUrlRepository extends JpaRepository<RagUrlEntity, Long> {
}
