package jira;

import bot.configs.BotConfig;
import dao.DaoFactory;
import exception.DAOException;
import exception.FailedAuthenticationException;
import model.ApplicationProject;
import model.JiraProject;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.Project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JiraController {
    private JiraAuthenticationController authenticationController;

    public JiraController(JiraAuthenticationController authenticationController) {
        this.authenticationController = authenticationController;
    }

    public JiraController(Long telegramGroupId) {
        this.authenticationController = authenticationController;
    }

    public void loadProjects() throws FailedAuthenticationException, JiraException, DAOException {
        JiraClient client = authenticationController.authenticate();

        ApplicationProject app = DaoFactory.getApplicationProjectDAO().getByName(BotConfig.get(BotConfig.PROJECT_NAME));

        List<Project> projects = client.getProjects();
        List<JiraProject> jiraProjects = new ArrayList<>();

        for (Project project : projects) {
            JiraProject jiraProject = new JiraProject();
            jiraProject.setName(project.getName());
            jiraProject.setJiraId(Long.valueOf(project.getId()));
            jiraProject.setCredentials(authenticationController.getCredentials());
            jiraProject.setApplicationProject(app);
            jiraProject.setLastUpdate(Calendar.getInstance());
            jiraProject.setKey(project.getKey());
            jiraProjects.add(jiraProject);
        }
        DaoFactory.getJiraProjectDao().insertAll(jiraProjects);
    }


//    // TODO: smthng with daoexception
//    public List<JiraProject> getProjects() throws DAOException {
//        Long now = Calendar.getInstance().getTimeInMillis();
//        Long fiveDays = 5 * 24 * 60 * 60 * 1000L;
//        List<JiraProject> projects;
//        projects = DaoFactory.getJiraProjectDao().getAll();
//        for (JiraProject project : projects) {
//            if (now - project.getLastUpdate().getTimeInMillis() > fiveDays) {
//                // TODO: reload projects from JIRA
//            }
//        }
//        return projects;
//    }
}
