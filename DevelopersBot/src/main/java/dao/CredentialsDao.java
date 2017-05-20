package dao;

import exception.DAOException;
import model.Credentials;

public interface CredentialsDao extends EntityDao<Credentials> {

    Credentials getJiraCredsByTelegramGroupId(Long telegramId) throws DAOException;
}
