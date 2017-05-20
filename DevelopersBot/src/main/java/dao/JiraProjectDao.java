package dao;

import exception.DAOException;
import model.JiraProject;

import java.util.List;

public interface JiraProjectDao extends EntityDao<JiraProject>{
    void insertAll(List<JiraProject> projects) throws DAOException;

    List<JiraProject> getAll(Long chatTelegramId) throws DAOException;
}
