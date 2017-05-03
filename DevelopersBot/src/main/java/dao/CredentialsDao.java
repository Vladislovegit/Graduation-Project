package dao;

import exception.DAOException;
import model.Credentials;

public interface CredentialsDao {

    Long insert(Credentials credentials) throws DAOException;

}
