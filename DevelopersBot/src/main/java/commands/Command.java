package commands;

import exception.IncorrectBotNameException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public interface Command {
    SendMessage execute(Message message) throws IncorrectBotNameException;
}
