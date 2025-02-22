package app;

import app.library.config.HibernateConnectionManager;
import app.library.config.PropertyConfig;
import app.library.model.Author;
import app.library.model.Book;
import app.library.model.Journal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class JavaLearnApp {


    public static void main(String[] args) {
        HibernateConnectionManager.initialize(new PropertyConfig());
        SessionFactory sessionFactory = HibernateConnectionManager.getSessionFactory();

        // Open a session
        try (Session session = sessionFactory.openSession()) {
            System.out.println("Hibernate session successfully opened!");

            Transaction transaction = session.beginTransaction();
            Long authorId = 1L;
            Author author = session.get(Author.class, authorId);
            Journal journal = session.get(Journal.class, 1L);
            Book book = session.get(Book.class, 1L);


            if (author != null) {
                System.out.println(author);
                System.out.println(journal);
                System.out.println(book);
            } else {
                System.out.println("No author found with ID: " + authorId);
            }
            transaction.commit();

            System.out.println("Transaction committed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error during Hibernate session operation: " + e.getMessage());
        } finally {
            HibernateConnectionManager.shutdown();
        }
    }
}
