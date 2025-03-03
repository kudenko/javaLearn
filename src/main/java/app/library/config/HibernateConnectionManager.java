package app.library.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateConnectionManager {

    private static HibernateConnectionManager connectionManager;
    private static SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(HibernateConnectionManager.class);

    private HibernateConnectionManager() {}

    public static synchronized HibernateConnectionManager initialize(PropertyConfig propertyConfig) {

        logger.info("Hibernate connection initialization start");
        if(connectionManager == null) {
            Configuration configuration = HibernateConfig.createConfiguration(propertyConfig);
            sessionFactory = SessionFactoryProvider.buildSessionFactory(configuration);
            connectionManager =  new HibernateConnectionManager();
        }
        logger.info("Hibernate connection initialization successful");
        return connectionManager;
    }

    public static SessionFactory getSessionFactory() {
        logger.info("Hibernate session factory initialization start");
        if (sessionFactory == null) {
            logger.error("Hibernate session factory initialization error. SessionFactory is null");
            throw new IllegalStateException("SessionFactory is not initialized. Call initialize() first.");
        }
        logger.info("Hibernate session factory initialization successful");
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            logger.info("Hibernate session factory shutting down");
            sessionFactory.close();
            sessionFactory = null;
            logger.info("Hibernate session factory shutting down successful");
        }
    }
}
