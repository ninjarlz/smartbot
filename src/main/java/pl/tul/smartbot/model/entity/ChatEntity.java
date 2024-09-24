package pl.tul.smartbot.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

import static pl.tul.smartbot.util.constant.entity.EntityProperties.CHAT_ID;

/**
 * The Entity class for the Chat item.
 */
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "chat")
@Table(uniqueConstraints = @UniqueConstraint(
        name = "unique_user_id_and_chat_type",
        columnNames = {"user_id", "chat_type"}
))
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChatType chatType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = CHAT_ID)
    @Fetch(FetchMode.SUBSELECT)
    private Set<DynamicUrlEntity> dynamicChatUrls;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = CHAT_ID)
    @Fetch(FetchMode.SUBSELECT)
    private Set<RagUrlEntity> ragChatUrls;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = CHAT_ID)
    @Fetch(FetchMode.SUBSELECT)
    private Set<ConfigurationPromptEntity> configurationPrompts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = CHAT_ID)
    @Fetch(FetchMode.SUBSELECT)
    private Set<MessageEntity> messages;

    @Column(nullable = false)
    private Long createdAt;

    @Column(nullable = false)
    private Long lastMessageTimestamp;

    @Column(nullable = false)
    private Long lastMessageId;
}
