package commands.impl;

import jira.JiraAuthenticationController;
import bot.BotStateManager;
import commands.Command;
import dao.CredentialsDao;
import dao.DaoFactory;
import dao.TelegramGroupDao;
import exception.DAOException;
import exception.FailedAuthenticationException;
import exception.IncorrectBotNameException;
import jira.JiraController;
import model.Credentials;
import model.TelegramGroup;
import net.rcarz.jiraclient.JiraException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.logging.BotLogger;
import utils.MessageUtil;

public class AuthenticateJiraCommand implements Command {
    private static final String LOG_TAG = AuthenticateJiraCommand.class.getSimpleName();
    private JiraAuthenticationController controller;
    private Credentials credentials;

    public AuthenticateJiraCommand() {
    }

    @Override
    public SendMessage execute(Message message) throws IncorrectBotNameException {
        SendMessage sendMessage = new SendMessage();
        String[] messageParts = MessageUtil.parseMessage(message);
        credentials = new Credentials();
        switch (messageParts.length) {
            case 1:
                sendMessage.setText("url login password");
                break;
            case 2:
                sendMessage.setText("login password");
                break;
            case 3:
                sendMessage.setText("password");
                break;
            case 4:
                credentials.setUrl(messageParts[1]);
                credentials.setLogin(messageParts[2]);
                credentials.setPassword(messageParts[3]);

                controller = new JiraAuthenticationController(credentials);
                if (controller.isSuccess()) {
                    try {
                        saveData(message.getChatId());
                    } catch (DAOException | FailedAuthenticationException | JiraException e) {
                        BotLogger.error(LOG_TAG, e);
                        sendMessage.setText("Failed to authenticate: " + e.getMessage());
                    }
                    sendMessage.setText("Successfully authenticated");
                }
                else sendMessage.setText("Failed to authenticate");
                break;
            default:
        }

        BotStateManager.resetState();
        return sendMessage;
    }

    private void saveData(Long telegramChatId) throws DAOException, FailedAuthenticationException, JiraException {
        CredentialsDao credentialsDao = DaoFactory.getCredentialsDao();
        credentialsDao.insert(credentials);

        TelegramGroupDao telegramGroupDao = DaoFactory.getTelegramGroupDao();
        TelegramGroup telegramGroup = telegramGroupDao.getByTelegramId(telegramChatId);
        telegramGroup.setJiraCreds(credentials);
        telegramGroupDao.update(telegramGroup);

        JiraController jiraController = new JiraController(controller);
        jiraController.loadProjects();
    }
}
