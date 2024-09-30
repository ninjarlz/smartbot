package pl.tul.smartbot.util.constant.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityConstants {
    // Properties
    public static final String CHAT_ID_PROPERTY = "chatId";
    public static final String USERS_PROPERTY = "users";

    // Columns
    public static final String USER_ID_COLUMN = "user_id";
    public static final String CHAT_ID_COLUMN = "chat_id";

    // Tables
    public static final String CHAT_USER_TABLE = "chat_user";
}
