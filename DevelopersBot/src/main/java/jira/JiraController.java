package jira;

import bot.configs.BotConfig;
import dao.DaoFactory;
import exception.DAOException;
import exception.FailedAuthenticationException;
import model.ApplicationProject;
import model.Credentials;
import model.JiraProject;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.Project;
import org.telegram.telegrambots.logging.BotLogger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JiraController {
    private static final String LOG_TAG = JiraController.class.getSimpleName();
    private JiraAuthenticationController authenticationController;

    private JiraClient client;
    private Long telegramId;

    public JiraController(JiraAuthenticationController authenticationController) {
        this.authenticationController = authenticationController;
    }

    public JiraController(Long telegramGroupId) {
        this.telegramId = telegramGroupId;
        this.authenticationController = getAuthenticationController(telegramGroupId);
    }

    private JiraAuthenticationController getAuthenticationController(Long telegramGroupId) {
        JiraAuthenticationController controller = null;
        try {
            Credentials credentials = DaoFactory.getCredentialsDao().getJiraCredsByTelegramGroupId(telegramGroupId);
            controller = new JiraAuthenticationController(credentials);
        } catch (DAOException e) {
            BotLogger.error(LOG_TAG, e);
//            throw new JiraException(e.getMessage(), e);
        }
        return controller;
    }

    public List<JiraProject> loadProjects() throws FailedAuthenticationException, JiraException, DAOException {
        client = authenticationController.authenticate();

        List<Project> projects = client.getProjects();
        List<JiraProject> jiraProjects = parseProjects(projects);
        DaoFactory.getJiraProjectDao().insertAll(jiraProjects);
        return jiraProjects;
    }

    // TODO: smthng with exceptions
    public List<JiraProject> getProjects() throws DAOException, JiraException, FailedAuthenticationException {
        Long now = Calendar.getInstance().getTimeInMillis();
        Long fiveDays = 5 * 24 * 60 * 60 * 1000L;
        List<JiraProject> projects = DaoFactory.getJiraProjectDao().getAll(telegramId);
        for (JiraProject project : projects) {
            if (now - project.getLastUpdate().getTimeInMillis() > fiveDays) {
                projects = loadProjects();
                break;
            }
        }
        return projects;
    }

    private List<JiraProject> parseProjects(List<Project> projects) throws DAOException {
        List<JiraProject> jiraProjects = new ArrayList<>();

        ApplicationProject app = DaoFactory.getApplicationProjectDAO().getByName(BotConfig.get(BotConfig.PROJECT_NAME));

        for (Project project : projects) {
            JiraProject jiraProject = new JiraProject();
            jiraProject.setName(project.getName());
            jiraProject.setJiraId(Long.valueOf(project.getId()));
            jiraProject.setApplicationProject(app);
            jiraProject.setLastUpdate(Calendar.getInstance());
            jiraProject.setKey(project.getKey());
            jiraProjects.add(jiraProject);
        }
        return jiraProjects;
    }
}
