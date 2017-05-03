package dao.impl;

import dao.TelegramLeadDao;
import exception.DAOException;
import model.TelegramLead;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

public class TelegramLeadDaoImpl implements TelegramLeadDao {
    @Override
    public Long insert(TelegramLead lead) throws DAOException {
        Transaction transaction = null;
        Long id;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            id = (Long) session.save(lead);
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
    public void delete(TelegramLead lead) throws DAOException {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(lead);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public void update(TelegramLead lead) throws DAOException {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(lead);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public Boolean exist(TelegramLead lead) throws DAOException {
        Transaction transaction = null;
        Boolean isExist;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            isExist = session
                    .createQuery("from TelegramLead where name = :name and telegramId = :telegramId")
                    .setParameter("name", lead.getName())
                    .setParameter("telegramId", lead.getTelegramId())
                    .setMaxResults(1)
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
    public TelegramLead getByApplicationProjectName(String projectName) throws DAOException {
        Transaction transaction = null;
        TelegramLead lead;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            lead = (TelegramLead) session
                    .createQuery("select new TelegramLead(lead.id, lead.name, lead.telegramId) from TelegramLead lead inner join ApplicationProject project " +
                            "on lead = project.lead where project.name = :name")
                    .setParameter("name", projectName)
                    .uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
        return lead;
    }
}
