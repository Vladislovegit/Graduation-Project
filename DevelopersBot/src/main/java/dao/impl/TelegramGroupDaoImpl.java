package dao.impl;

import dao.TelegramGroupDao;
import exception.DAOException;
import model.TelegramGroup;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

public class TelegramGroupDaoImpl implements TelegramGroupDao {

    @Override
    public Long insert(TelegramGroup group) throws DAOException {
        Transaction transaction = null;
        Long id;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            id = (Long) session.save(group);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
        return id;
    }

    @Override
    public void delete(TelegramGroup group) throws DAOException {
        Transaction transaction = null;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(group);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public void update(TelegramGroup group) throws DAOException {
        Transaction transaction = null;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(group);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public Boolean exist(Long groupTelegramId) throws DAOException {
        Transaction transaction = null;
        Boolean isExist;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            isExist = session.createQuery("from TelegramGroup where telegramId = :telegramId")
                    .setParameter("telegramId", groupTelegramId)
                    .uniqueResult() != null;
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
        return isExist;
    }

    @Override
    public TelegramGroup getByTelegramId(Long id) throws DAOException {
        Transaction transaction = null;
        TelegramGroup group;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            group = (TelegramGroup) session.createQuery("from TelegramGroup where telegramId = :telegramId")
                    .setParameter("telegramId", id)
                    .uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
        return group;
    }
}
