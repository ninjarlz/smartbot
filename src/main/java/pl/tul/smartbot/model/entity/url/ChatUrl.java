package pl.tul.smartbot.model.entity.url;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static java.util.Objects.nonNull;

@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ChatUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    protected Long id;

    @Column(nullable = false)
    protected String url;

    protected String vaultCredentialsKey;

    @Column(nullable = false)
    protected Long chatId;

    public boolean requiresAuthentication() {
        return nonNull(vaultCredentialsKey);
    }
}
