package handlers;

import bot.BotStateManager;
import bot.configs.BotConfig;
import commands.Command;
import commands.CommandHandler;
import commands.CommandName;
import commands.CommandRecognizer;
import dao.DaoFactory;
import dao.TelegramGroupDao;
import exception.DAOException;
import exception.IncorrectBotNameException;
import model.ApplicationProject;
import model.TelegramGroup;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.logging.BotLogger;

public class IncomingMessageHandler {
    private static final String LOG_TAG = IncomingMessageHandler.class.getSimpleName();

    public static SendMessage handle(Update update) {
        SendMessage sendMessage = new SendMessage();
        try {
            saveGroupIfNotExist(update);

            CommandName commandName;
            if (BotStateManager.isWaiting()) {
                commandName = CommandRecognizer.recognize(update);
                BotStateManager.setCommand(commandName);
            } else {
                commandName = BotStateManager.getCommand();
            }

            Command command = CommandHandler.getCommand(commandName);
            Message message = update.getMessage();
            sendMessage = command.execute(message);
        } catch (IncorrectBotNameException e) {
            BotLogger.error(LOG_TAG, e);
            return null;
        } catch (DAOException e) {
            BotLogger.error(LOG_TAG, e);
            sendMessage.setText("Something went wrong. " + e.getMessage());
        }

        sendMessage.setChatId(update.getMessage().getChatId());
        return sendMessage;
    }

    private static void saveGroupIfNotExist(Update update) throws DAOException {
        TelegramGroupDao telegramGroupDao = DaoFactory.getTelegramGroupDao();
        Chat chat = update.getMessage().getChat();
        if (!telegramGroupDao.exist(chat.getId())) {
            TelegramGroup telegramGroup = new TelegramGroup();
            telegramGroup.setTelegramId(chat.getId());
            telegramGroup.setName(chat.getTitle());
            ApplicationProject app = DaoFactory.getApplicationProjectDAO().getByName(BotConfig.get(BotConfig.PROJECT_NAME));
            telegramGroup.setProject(app);
            telegramGroupDao.insert(telegramGroup);
        }
    }
}
