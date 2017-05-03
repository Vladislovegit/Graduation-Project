package commands.impl;

import bot.BotStateManager;
import commands.Command;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class UnknownCommand implements Command {

    public UnknownCommand() {
    }

    @Override
    public SendMessage execute(Message message) {
        BotStateManager.resetState();
        return new SendMessage().setText("I don't know this command. Check, if you made mistakes");
    }
}
