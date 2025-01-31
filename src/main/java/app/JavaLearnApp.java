package app;

import app.library.author.Author;
import app.library.config.DatabaseConnectionManager;
import app.library.config.PropertyConfig;
import app.library.model.Book;
import app.library.model.Journal;
import app.library.storage.*;
import jakarta.servlet.ServletContext;

public class JavaLearnApp {

    public static AuthorRepositoryCustom<Author> authorRepository;

    public static void main(String[] args) {
        PropertyConfig propertyConfig = new PropertyConfig();
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(
                propertyConfig.getDbHost(),
                propertyConfig.getDbName(),
                propertyConfig.getDbUsername(),
                propertyConfig.getDbPassword()
        );
        BookRepositoryCustom<Book> bookRepository = new BookRepository(connectionManager);
        AuthorRepositoryCustom<Author> authorRepository = new AuthorRepository(connectionManager);
        JournalRepositoryCustom<Journal> journalRepository = new JournalRepository(connectionManager);
        //System.out.println(journalRepository.findById(1l));
//        journalRepository.save(new Journal(1, "test", 23, 33, 1456));
//        System.out.println(journalRepository.update(new Journal(1, "UPDATED", 20, 2, 1999)));
//        bookRepository.save(new Book("test", 225, 1));
//        authorRepository.save(new Author("test1", "test2", "test@gmail.com"));
//        System.out.println(bookRepository.findAll());
//        System.out.println(bookRepository.findById(2));
//          bookRepository.delete(2L);
//        System.out.println(bookRepository.update(new Book(3,"UPDATED", 225, 1)));

//        authorRepository.save(new Author("test2", "test2", "test2@gmail.com"));
//        System.out.println(authorRepository.findAll());

        //????
//        authorRepository.delete(1l);
//        System.out.println(authorRepository.findById(1L));
        // System.out.println(authorRepository.findByEmail("test@gmail.com"));
        //System.out.println(bookRepository.findBooksByAuthorId(2L));
        Dispatcher.initApp(bookRepository, authorRepository, journalRepository);
    }

    public static void initMyLearnApp() {
        System.out.println("I'm from init");
        PropertyConfig propertyConfig = new PropertyConfig();
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(
                propertyConfig.getDbHost(),
                propertyConfig.getDbName(),
                propertyConfig.getDbUsername(),
                propertyConfig.getDbPassword()
        );
        BookRepositoryCustom<Book> bookRepository = new BookRepository(connectionManager);
        if(authorRepository == null) {
            authorRepository = new AuthorRepository(connectionManager);
        }
        JournalRepositoryCustom<Journal> journalRepository = new JournalRepository(connectionManager);
//        Dispatcher.initApp(bookRepository, authorRepository, journalRepository);
    }
}
