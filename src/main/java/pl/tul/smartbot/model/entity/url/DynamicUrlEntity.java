package pl.tul.smartbot.model.entity.url;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * The Entity class for the Dynamic Url item.
 */
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@Entity(name = "dynamic_url")
public class DynamicUrlEntity extends ChatUrl {
}
