package commands.impl;

import bot.BotStateManager;
import commands.Command;
import bot.configs.CommandsConfig;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class HelpCommand implements Command {

    public HelpCommand() {
    }

    @Override
    public SendMessage execute(Message message) {
        StringBuilder stringBuilder = new StringBuilder("Here you can see a full list of supported <b>commands</b>:\n")
                .append("\n");
        for (Object string : CommandsConfig.getCommands()) {
            stringBuilder.append(string.toString());
            stringBuilder.append("\n");
        }

        BotStateManager.resetState();
        return new SendMessage().enableHtml(true).setText(stringBuilder.toString());
    }
}
