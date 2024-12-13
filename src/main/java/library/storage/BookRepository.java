package library.storage;

import library.config.DatabaseConnectionManager;
import library.model.Book;

import java.util.List;

public class BookRepository implements Repository<Book> {

    private final DatabaseConnectionManager connectionManager;

    public BookRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void save(Book entity) {

    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public void delete(Book entity) {

    }

    @Override
    public Book findById(long id) {
        return null;
    }

    @Override
    public void saveAll(List<Book> entities) {

    }

    @Override
    public void print() {

    }

    @Override
    public Book update(Book entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
