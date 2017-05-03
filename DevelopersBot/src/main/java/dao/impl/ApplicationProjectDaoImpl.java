package dao.impl;

import dao.ApplicationProjectDao;
import exception.DAOException;
import model.ApplicationProject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

public class ApplicationProjectDaoImpl implements ApplicationProjectDao {

    public Long insert(ApplicationProject project) throws DAOException {
        Transaction transaction = null;
        Long id;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            id = (Long) session.save(project);
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
    public void delete(ApplicationProject project) throws DAOException {
        Transaction transaction = null;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(project);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public void update(ApplicationProject project) throws DAOException {
        Transaction transaction = null;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(project);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public Boolean exist(ApplicationProject project) throws DAOException {
        Transaction transaction = null;
        Boolean isExist;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            isExist = session
                    .createQuery("from ApplicationProject project where project.name = :name and project.lead = :lead")
                    .setParameter("name", project.getName())
                    .setParameter("lead", project.getLead())
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
    public ApplicationProject getByName(String name) throws DAOException {
        Transaction transaction = null;
        ApplicationProject project;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            project = (ApplicationProject) session.createQuery("from ApplicationProject where name = :name")
                    .setParameter("name", name)
                    .uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
        return project;
    }

    @Override
    public ApplicationProject getByChatTelegramId(Long telegramId) throws DAOException {
        Transaction transaction = null;
        ApplicationProject project;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            project = (ApplicationProject) session.createQuery("select new ApplicationProject(project.id, project.name, project.lead) " +
                    "from ApplicationProject project inner join TelegramGroup group on group.project = project where group.telegramId = :telegramId")
                    .setParameter("telegramId", telegramId)
                    .uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
        return project;
    }
}
