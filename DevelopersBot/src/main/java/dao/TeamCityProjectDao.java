package dao;

import exception.DAOException;
import model.TeamCityProject;

public interface TeamCityProjectDao {
    Long insert(TeamCityProject project) throws DAOException;
}
