package pl.tul.smartbot.util.constant.rest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiUrls {
    public static final String API_PREFIX_V1 = "/v1";
    public static final String CHAT_ENDPOINT_V1 = API_PREFIX_V1 + "/chat";
    public static final String MESSAGE_PATH = "/message";
}
