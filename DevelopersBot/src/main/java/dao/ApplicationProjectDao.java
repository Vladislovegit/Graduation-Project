package dao;

import exception.DAOException;
import model.ApplicationProject;

public interface ApplicationProjectDao extends EntityDao<ApplicationProject>{

    Boolean exist(ApplicationProject project) throws DAOException;

    ApplicationProject getByName(String name) throws DAOException;

    ApplicationProject getByChatTelegramId(Long telegramId) throws DAOException;
}
