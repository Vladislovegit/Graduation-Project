package bot;

import bot.configs.BotConfig;
import handlers.IncomingMessageHandler;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

public class BotHandler extends TelegramLongPollingBot {
    private static final String LOG_TAG = BotHandler.class.getSimpleName();

    @Override
    public void onUpdateReceived(Update update) {
        try {
            SendMessage sendMessage = IncomingMessageHandler.handle(update);
            if (sendMessage != null) sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            BotLogger.error(LOG_TAG, e);
        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.get(BotConfig.BOT_NAME);
    }

    @Override
    public String getBotToken() {
        return BotConfig.get(BotConfig.BOT_TOKEN);
    }
}
