package commands.impl;

import commands.Command;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class StartCommand implements Command {

    public StartCommand() {
    }

    @Override
    public SendMessage execute(Message message) {
        return new HelpCommand().execute(message);
    }
}
