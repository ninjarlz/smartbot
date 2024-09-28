package pl.tul.smartbot.exception.chat;

public class ChatNotFoundException extends Exception {
    private static final String MESSAGE = "Chat with id '%d' not found";

    public ChatNotFoundException(long chatId) {
        super(MESSAGE.formatted(chatId));
    }
}
