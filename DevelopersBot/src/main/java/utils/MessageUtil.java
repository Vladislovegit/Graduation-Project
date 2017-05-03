package utils;

import bot.configs.BotConfig;
import exception.IncorrectBotNameException;
import org.telegram.telegrambots.api.objects.Message;

public class MessageUtil {
    public static final String COMMAND_INIT_CHAR = "/";

    public static String[] parseMessage(Message message) throws IncorrectBotNameException {
        String[] messageParts = message.getText().split("@");
        if (messageParts.length > 1) {
            if (!isCorrectBotName(messageParts[1])) {
                throw new IncorrectBotNameException("Incorrect bot name.");
            }
        }
        return messageParts[0].replaceFirst(COMMAND_INIT_CHAR, "").split(" ");
    }

    public static boolean isCorrectBotName(String name) {
        return name.equals(BotConfig.get(BotConfig.BOT_NAME));
    }
}
