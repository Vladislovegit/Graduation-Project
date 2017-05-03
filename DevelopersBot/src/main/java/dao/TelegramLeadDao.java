package dao;

import exception.DAOException;
import model.TelegramLead;

public interface TelegramLeadDao {
    Long insert(TelegramLead lead) throws DAOException;

    void delete(TelegramLead lead) throws DAOException;

    void update(TelegramLead lead) throws DAOException;

    Boolean exist(TelegramLead lead) throws DAOException;

    TelegramLead getByApplicationProjectName(String projectName) throws DAOException;
}
