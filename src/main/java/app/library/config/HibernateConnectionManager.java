package app.library.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateConnectionManager {

    private static SessionFactory sessionFactory;
    private final SessionFactoryProvider sessionFactoryProvider;
    private final HibernateConfig hibernateConfig;
    private static final Logger logger = LoggerFactory.getLogger(HibernateConnectionManager.class);

    private HibernateConnectionManager(PropertyConfig propertyConfig, HibernateConfig hibernateConfig, SessionFactoryProvider sessionFactoryProvider) {
        this.hibernateConfig = hibernateConfig;
        this.sessionFactoryProvider = sessionFactoryProvider;
        initialize(propertyConfig);
    }

    public void initialize(PropertyConfig propertyConfig) {
        Configuration configuration = hibernateConfig.createConfiguration(propertyConfig);
        sessionFactory = sessionFactoryProvider.buildSessionFactory(configuration);
    }

    public SessionFactory getSessionFactory() {
        logger.info("Hibernate session factory initialization start");
        if (sessionFactory == null) {
            logger.error("Hibernate session factory initialization error. SessionFactory is null");
            throw new IllegalStateException("SessionFactory is not initialized. Call initialize() first.");
        }
        logger.info("Hibernate session factory initialization successful");
        return sessionFactory;
    }

    public void shutdown() {
        if (sessionFactory != null) {
            logger.info("Hibernate session factory shutting down");
            sessionFactory.close();
            sessionFactory = null;
            logger.info("Hibernate session factory shutting down successful");
        }
    }
}
