package commands;

import exception.IncorrectBotNameException;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import utils.MessageUtil;

import static utils.MessageUtil.COMMAND_INIT_CHAR;

public class CommandRecognizer {

    public static CommandName recognize(Update update) throws IncorrectBotNameException {

        if (!update.hasMessage() || !update.getMessage().hasText() || !isCommand(update.getMessage())) {
            return CommandName.NON_COMMAND;
        }

        String[] messageParts = MessageUtil.parseMessage(update.getMessage());

        if (!isKnownCommand(messageParts[0].toUpperCase())) {
            return CommandName.UNKNOWN_COMMAND;
        }

        return CommandName.valueOf(messageParts[0].toUpperCase());
    }

    private static Boolean isCommand(Message message) {
        return message.getText().startsWith(COMMAND_INIT_CHAR);
    }

    private static Boolean isKnownCommand(String command) {
        for (CommandName commandName : CommandName.values()) {
            if (command.equals(commandName.toString()))
                return true;
        }
        return false;
    }
}
