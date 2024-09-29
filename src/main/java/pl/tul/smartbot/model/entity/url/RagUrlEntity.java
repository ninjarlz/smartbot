package pl.tul.smartbot.model.entity.url;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * The Entity class for the RAG Url item.
 */
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@Entity(name = "rag_url")
public class RagUrlEntity extends ChatUrl {
}
