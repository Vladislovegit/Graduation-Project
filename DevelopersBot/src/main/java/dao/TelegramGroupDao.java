package dao;

import exception.DAOException;
import model.TelegramGroup;

public interface TelegramGroupDao {
    Long insert(TelegramGroup group) throws DAOException;

    void delete(TelegramGroup group) throws DAOException;

    void update(TelegramGroup group) throws DAOException;

    Boolean exist(Long groupTelegramId) throws DAOException;

    TelegramGroup getByTelegramId(Long id) throws DAOException;
}
