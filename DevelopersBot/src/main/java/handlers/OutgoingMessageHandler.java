package handlers;

import org.telegram.telegrambots.api.methods.send.SendMessage;

public interface OutgoingMessageHandler {
    SendMessage prepare(SendMessage message);
}
