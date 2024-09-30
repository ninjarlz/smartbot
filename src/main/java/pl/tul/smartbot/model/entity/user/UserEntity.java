package pl.tul.smartbot.model.entity.user;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import pl.tul.smartbot.model.entity.chat.ChatEntity;

import java.util.Set;

import static pl.tul.smartbot.util.constant.entity.EntityConstants.CHAT_ID_COLUMN;
import static pl.tul.smartbot.util.constant.entity.EntityConstants.CHAT_USER_TABLE;
import static pl.tul.smartbot.util.constant.entity.EntityConstants.USERS_PROPERTY;
import static pl.tul.smartbot.util.constant.entity.EntityConstants.USER_ID_COLUMN;

@Entity(name = "user")
public class UserEntity {

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = CHAT_USER_TABLE, joinColumns = @JoinColumn(name = USER_ID_COLUMN))
    @Column(name = CHAT_ID_COLUMN, insertable = false, updatable = false)
    private Set<Long> chatIds;

    @ManyToMany(mappedBy = USERS_PROPERTY)
    private Set<ChatEntity> chats;
}
