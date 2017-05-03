package dao.impl;

import dao.CredentialsDao;
import exception.DAOException;
import model.Credentials;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

public class CredentialsDaoImpl implements CredentialsDao{

    @Override
    public Long insert(Credentials credentials) throws DAOException {
        Transaction transaction = null;
        Long id;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            id = (Long) session.save(credentials);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
        return id;
    }
}
