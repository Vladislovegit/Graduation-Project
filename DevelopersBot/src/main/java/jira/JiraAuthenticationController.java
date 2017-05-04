package jira;

import exception.FailedAuthenticationException;
import model.Credentials;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;
import org.telegram.telegrambots.logging.BotLogger;

public class JiraAuthenticationController {
    private static final String LOG_TAG = JiraAuthenticationController.class.getSimpleName();

    private Credentials credentials;

    public JiraAuthenticationController(Credentials credentials) {
        this.credentials = credentials;
    }

    public Boolean isSuccess() {
        try {
            return authenticate() != null;
        } catch (FailedAuthenticationException e) {
            e.printStackTrace();
        }
        return false;
    }

    public JiraClient authenticate() throws FailedAuthenticationException {
        JiraClient client;
        try {
            BasicCredentials basicCredentials = new BasicCredentials(credentials.getLogin(), credentials.getPassword());
            client = new JiraClient(credentials.getUrl(), basicCredentials);
        } catch (Exception e) {
            BotLogger.error(LOG_TAG, e);
            throw new FailedAuthenticationException("Failed to authenticate in JIRA.");
        }
        return client;
    }
}
