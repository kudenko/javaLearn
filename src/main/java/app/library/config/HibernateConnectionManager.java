package app.library.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnectionManager {

    private static HibernateConnectionManager connectionManager;
    private static SessionFactory sessionFactory;

    private HibernateConnectionManager() {}

    public static synchronized HibernateConnectionManager initialize(PropertyConfig propertyConfig) {
        if(connectionManager == null) {
            Configuration configuration = HibernateConfig.createConfiguration(propertyConfig);
            sessionFactory = SessionFactoryProvider.buildSessionFactory(configuration);
            connectionManager =  new HibernateConnectionManager();
        }
        return connectionManager;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory is not initialized. Call initialize() first.");
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }
}
