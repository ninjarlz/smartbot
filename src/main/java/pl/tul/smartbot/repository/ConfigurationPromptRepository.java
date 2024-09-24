package pl.tul.smartbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.smartbot.model.entity.ConfigurationPromptEntity;

/**
 * Interface describing public operations for configuration prompts persistence.
 */
public interface ConfigurationPromptRepository extends JpaRepository<ConfigurationPromptEntity, Long> {
}
