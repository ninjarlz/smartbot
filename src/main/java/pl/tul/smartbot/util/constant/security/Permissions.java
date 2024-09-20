package pl.tul.smartbot.util.constant.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Permissions {

    // Permissions
    public static final String WRITE_CHATBOT_PERMISSION = "WRITE_CHATBOT";
    public static final String READ_CHATBOT_PERMISSION = "READ_CHATBOT";

    //  Spring security permission expressions
    private static final String HAS_ANY_AUTHORITY_EXPRESSION = "hasAnyAuthority('";
    public static final String WRITE_CHATBOT_PERMISSION_EXPRESSION = HAS_ANY_AUTHORITY_EXPRESSION + WRITE_CHATBOT_PERMISSION + "')";
    public static final String READ_WRITE_CHATBOT_PERMISSION_EXPRESSION = HAS_ANY_AUTHORITY_EXPRESSION + READ_CHATBOT_PERMISSION + "','" + WRITE_CHATBOT_PERMISSION + "')";
}
