package bot.configs;

import org.telegram.telegrambots.logging.BotLogger;

import java.io.IOException;
import java.util.Properties;

public class BotConfig {
    private static final String LOG_TAG = BotConfig.class.getSimpleName();

    private static final String PROPERTIES_PATH = "config.bot.properties";

    public static final String BOT_NAME = "config.bot.name";
    public static final String BOT_TOKEN = "config.bot.token";
    public static final String LEAD_ID = "config.lead.id";
    public static final String LEAD_NAME = "config.lead.name";
    public static final String PROJECT_NAME = "config.project.name";

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(BotConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH));
        } catch (IOException e) {
            BotLogger.error(LOG_TAG, e);
        }
    }

    public static String get(String property) {
        return properties.getProperty(property);
    }
}
