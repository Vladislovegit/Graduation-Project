package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.telegram.telegrambots.logging.BotLogger;

public class HibernateSessionFactory implements AutoCloseable {
    private static final String LOG_TAG = "HIBERNATESESSIONFACTORY";

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();

        } catch (Throwable ex) {
            BotLogger.error(LOG_TAG, ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        getSessionFactory().close();
    }
}
