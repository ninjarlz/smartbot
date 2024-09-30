package pl.tul.smartbot.model.entity.chat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.tul.smartbot.model.entity.message.MessageEntity;
import pl.tul.smartbot.model.entity.prompt.ConfigurationPromptEntity;
import pl.tul.smartbot.model.entity.url.DynamicUrlEntity;
import pl.tul.smartbot.model.entity.url.RagUrlEntity;
import pl.tul.smartbot.model.entity.user.UserEntity;

import java.util.Set;

import static pl.tul.smartbot.util.constant.entity.EntityConstants.CHAT_ID_COLUMN;
import static pl.tul.smartbot.util.constant.entity.EntityConstants.CHAT_ID_PROPERTY;
import static pl.tul.smartbot.util.constant.entity.EntityConstants.CHAT_USER_TABLE;
import static pl.tul.smartbot.util.constant.entity.EntityConstants.USER_ID_COLUMN;

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
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LlmType llmType;

    @Column(nullable = false)
    private Long createdAt;

    @Column(nullable = false)
    private Long lastMessageTimestamp;

    @Column(nullable = false)
    private Long lastMessageId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = CHAT_USER_TABLE, joinColumns = @JoinColumn(name = CHAT_ID_COLUMN))
    @Column(name = USER_ID_COLUMN, updatable = false, insertable = false)
    private Set<Long> userIds;

    @ManyToMany
    @JoinTable(
            name = CHAT_USER_TABLE,
            joinColumns = @JoinColumn(name = CHAT_ID_COLUMN),
            inverseJoinColumns = @JoinColumn(name = USER_ID_COLUMN))
    private Set<UserEntity> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = CHAT_ID_PROPERTY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<DynamicUrlEntity> dynamicChatUrls;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = CHAT_ID_PROPERTY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<RagUrlEntity> ragChatUrls;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = CHAT_ID_PROPERTY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<ConfigurationPromptEntity> configurationPrompts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = CHAT_ID_PROPERTY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<MessageEntity> messages;
}
