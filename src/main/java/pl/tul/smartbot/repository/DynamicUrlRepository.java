package pl.tul.smartbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.smartbot.model.entity.url.DynamicUrlEntity;

/**
 * Interface describing public operations for dynamic URLs persistence.
 */
public interface DynamicUrlRepository extends JpaRepository<DynamicUrlEntity, Long> {
}
