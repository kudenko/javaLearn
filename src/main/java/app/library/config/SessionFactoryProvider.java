package app.library.config;

import app.library.model.Author;
import app.library.model.Book;
import app.library.model.Journal;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionFactoryProvider {

    private static final Logger logger = LoggerFactory.getLogger(SessionFactoryProvider.class);

    public SessionFactory buildSessionFactory(Configuration configuration) {
        logger.info("Session factory configuration initialization");
        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Journal.class);
        configuration.addAnnotatedClass(Book.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        logger.info("Session factory configuration initialization successful");

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
