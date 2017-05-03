package dao.impl;

import dao.JiraProjectDao;
import exception.DAOException;
import model.JiraProject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class JiraProjectDaoImpl implements JiraProjectDao {
    @Override
    public Long insert(JiraProject project) throws DAOException {
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
    public void insertAll(List<JiraProject> projects) throws DAOException {
        Transaction transaction = null;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            for (JiraProject project : projects) {
                session.save(project);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public List<JiraProject> getAll(Long chatTelegramId) throws DAOException {
        List<JiraProject> projects = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select project from JiraProject project inner join ApplicationProject app on project.applicationProject = app inner join TelegramGroup chat on chat.project = app where chat.telegramId = :telegramId")
                    .setParameter("telegramId", chatTelegramId);
            for (Object project : query.getResultList()) {
                projects.add((JiraProject) project);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
        return projects;
    }
}
