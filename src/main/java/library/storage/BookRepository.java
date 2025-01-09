package library.storage;

import library.config.DatabaseConnectionManager;
import library.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements BookRepositoryCustom<Book> {

    private static final String INSERT = "INSERT INTO book(name, count_pages, author_id) VALUES (?,?,?)";

    private static final String FIND_ALL = "SELECT * FROM book;";

    private static final String DELETE = "DELETE FROM book WHERE id = ?;";

    private static final String SELECT = "SELECT * FROM book WHERE id = ?;";

    private static final String UPDATE = "UPDATE book SET name = ?, count_pages = ?, author_id = ? WHERE id = ?";

    private static final String SELECT_BY_AUTHOR_ID = "SELECT * FROM book WHERE author_id = ?;";


    private final DatabaseConnectionManager connectionManager;

    public BookRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void save(Book entity) {
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getCountPages());
            preparedStatement.setLong(3, entity.getAuthorId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int pagesCount = resultSet.getInt("count_pages");
                long authorId = resultSet.getLong("author_id");
                Book book = new Book(id, name, pagesCount, authorId);
                books.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public void delete(Book entity) {
        delete(entity.getId());
    }

    @Override
    public Book findById(long id) {
        Book book = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int pagesCount = resultSet.getInt("count_pages");
                long authorId = resultSet.getLong("author_id");
                book = new Book(id, name, pagesCount, authorId);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public void saveAll(List<Book> entities) {
        entities.forEach(this::save);
    }

    @Override
    public void print() {
        findAll().forEach(System.out::println);
    }

    @Override
    public Book update(Book entity) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getCountPages());
            preparedStatement.setLong(3, entity.getAuthorId());
            preparedStatement.setLong(4, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findById(entity.getId());
    }

    @Override
    public void delete(Long id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findBooksByAuthorId(Long authorId) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_AUTHOR_ID)) {
            preparedStatement.setLong(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int pagesCount = resultSet.getInt("count_pages");
                Book book = new Book(id, name, pagesCount, authorId);
                books.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
}
