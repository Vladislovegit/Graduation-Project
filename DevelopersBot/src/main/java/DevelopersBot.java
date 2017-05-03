import bot.BotHandler;
import bot.configs.BotConfig;
import dao.ApplicationProjectDao;
import dao.DaoFactory;
import dao.TelegramLeadDao;
import exception.DAOException;
import model.ApplicationProject;
import model.TelegramLead;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;
import org.telegram.telegrambots.logging.BotsFileHandler;

import java.io.IOException;
import java.util.logging.Level;

public class DevelopersBot {
    private static final String LOG_TAG = "MAIN";

    public static void main(String[] args) {
        try {
            BotLogger.setLevel(Level.WARNING);
            BotLogger.registerLogger(new BotsFileHandler("./logs/TelegramBots%g.%u.log"));
        } catch (IOException e) {
            BotLogger.severe(LOG_TAG, e);
        }

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        saveConfigInfo();

        try {
            botsApi.registerBot(new BotHandler());
        } catch (TelegramApiException e) {
            BotLogger.error(LOG_TAG, e);
        }
    }

    private static void saveConfigInfo() {
        ApplicationProject project = new ApplicationProject();
        project.setName(BotConfig.get(BotConfig.PROJECT_NAME));

        TelegramLead lead = new TelegramLead();
        lead.setName(BotConfig.get(BotConfig.LEAD_NAME));
        lead.setTelegramId(Long.valueOf(BotConfig.get(BotConfig.LEAD_ID)));

        ApplicationProjectDao applicationProjectDAO = DaoFactory.getApplicationProjectDAO();
        TelegramLeadDao telegramLeadDao = DaoFactory.getTelegramLeadDao();

        try {
            if (!telegramLeadDao.exist(lead)) {
                telegramLeadDao.insert(lead);
            } else {
                lead = telegramLeadDao.getByApplicationProjectName(project.getName());
            }
            project.setLead(lead);
            if (!applicationProjectDAO.exist(project)) {
                applicationProjectDAO.insert(project);
            }
        } catch (DAOException e) {
            BotLogger.error(LOG_TAG, e);
        }
    }
}
