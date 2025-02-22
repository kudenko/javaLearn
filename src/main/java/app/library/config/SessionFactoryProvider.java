package app.library.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryProvider {
    public static SessionFactory buildSessionFactory(Configuration configuration) {

        configuration.addResource("Author.hbm.xml");
        configuration.addResource("Journal.hbm.xml");
        configuration.addResource("Book.hbm.xml");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
