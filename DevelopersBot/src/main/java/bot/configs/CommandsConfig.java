package bot.configs;

import org.telegram.telegrambots.logging.BotLogger;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class CommandsConfig {
    private static final String LOG_TAG = "COMMANDSCONFIG";

    private static final String PROPERTIES_PATH = "bot.commands.properties";

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(BotConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH));

        } catch (IOException e) {
            BotLogger.error(LOG_TAG, e);
        }
    }

    public static Collection getCommands() {
        return properties.values();
    }
}
