package app.library.config;

import app.library.model.Author;
import app.library.model.Book;
import app.library.model.Journal;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryProvider {
    public static SessionFactory buildSessionFactory(Configuration configuration) {

        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Journal.class);
        configuration.addAnnotatedClass(Book.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
