package dao;

import exception.DAOException;

public interface EntityDao<EntityType> {

    Long insert(EntityType entity) throws DAOException;

    void delete(EntityType entity) throws DAOException;

    void update(EntityType entity) throws DAOException;
}
