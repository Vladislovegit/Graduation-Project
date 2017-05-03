package commands.impl;

import bot.BotStateManager;
import commands.Command;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class NonCommand implements Command {

    public NonCommand() {
    }

    @Override
    public SendMessage execute(Message message) {
        BotStateManager.resetState();
        return null;
    }
}
