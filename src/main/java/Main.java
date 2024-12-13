import library.author.Author;
import library.config.DatabaseConnectionManager;
import library.model.Book;
import library.model.Journal;
import library.storage.AuthorRepository;
import library.storage.BookRepository;
import library.storage.JournalRepository;
import library.storage.Repository;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager("localhost", "learning", "postgres", "admin");
        try {
            Connection connection = connectionManager.getConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Repository<Book> bookRepository = new BookRepository(connectionManager);
        Repository<Author> authorRepository = new AuthorRepository(connectionManager);
        Repository<Journal> journalRepository = new JournalRepository(connectionManager);
        Dispatcher.initApp(bookRepository, authorRepository, journalRepository);
    }
}
