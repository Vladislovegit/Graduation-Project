package dao;

import exception.DAOException;
import model.ApplicationProject;

public interface ApplicationProjectDao {
    Long insert(ApplicationProject project) throws DAOException;

    void delete(ApplicationProject project) throws DAOException;

    void update(ApplicationProject project) throws DAOException;

    Boolean exist(ApplicationProject project) throws DAOException;

    ApplicationProject getByName(String name) throws DAOException;

    ApplicationProject getByChatTelegramId(Long telegramId) throws DAOException;
}
