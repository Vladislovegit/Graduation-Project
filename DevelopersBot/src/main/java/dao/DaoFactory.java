package dao;

import dao.impl.*;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
    private static final Map<DaoType, Object> daos = new HashMap<>();

    static {
        daos.put(DaoType.APPLICATION_PROJECT, new ApplicationProjectDaoImpl());
        daos.put(DaoType.CREDENTIALS, new CredentialsDaoImpl());
        daos.put(DaoType.TELEGRAM_LEAD, new TelegramLeadDaoImpl());
        daos.put(DaoType.TEAMCITY_PROJECT, new TeamCityProjectDaoImpl());
        daos.put(DaoType.JIRA_PROJECT, new JiraProjectDaoImpl());
        daos.put(DaoType.TELEGRAM_GROUP, new TelegramGroupDaoImpl());
    }

    public static ApplicationProjectDao getApplicationProjectDAO() {
        return (ApplicationProjectDao)daos.get(DaoType.APPLICATION_PROJECT);
    }

    public static CredentialsDao getCredentialsDao() {
        return (CredentialsDao) daos.get(DaoType.CREDENTIALS);
    }

    public static TelegramLeadDao getTelegramLeadDao() {
        return (TelegramLeadDao) daos.get(DaoType.TELEGRAM_LEAD);
    }

    public static JiraProjectDao getJiraProjectDao() {
        return (JiraProjectDao) daos.get(DaoType.JIRA_PROJECT);
    }

    public static TeamCityProjectDao getTeamCityProjectDao() {
        return (TeamCityProjectDao) daos.get(DaoType.TEAMCITY_PROJECT);
    }

    public static TelegramGroupDao getTelegramGroupDao() {
        return (TelegramGroupDao) daos.get(DaoType.TELEGRAM_GROUP);
    }
}
