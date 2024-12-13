package library.storage;

import library.author.Author;
import library.config.DatabaseConnectionManager;
import library.model.Book;

import java.util.List;

public class AuthorRepository implements Repository<Author>{

    private final DatabaseConnectionManager connectionManager;

    public AuthorRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void save(Author entity) {

    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public void delete(Author entity) {

    }

    @Override
    public Author findById(long id) {
        return null;
    }

    @Override
    public void saveAll(List<Author> entities) {

    }

    @Override
    public void print() {

    }

    @Override
    public Author update(Author entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
