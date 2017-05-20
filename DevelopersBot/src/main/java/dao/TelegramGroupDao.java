package dao;

import exception.DAOException;
import model.TelegramGroup;

public interface TelegramGroupDao extends EntityDao<TelegramGroup> {

    Boolean exist(Long groupTelegramId) throws DAOException;

    TelegramGroup getByTelegramId(Long id) throws DAOException;
}
