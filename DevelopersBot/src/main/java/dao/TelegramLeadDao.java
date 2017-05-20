package dao;

import exception.DAOException;
import model.TelegramLead;

public interface TelegramLeadDao extends EntityDao<TelegramLead> {

    Boolean exist(TelegramLead lead) throws DAOException;

    TelegramLead getByApplicationProjectName(String projectName) throws DAOException;
}
