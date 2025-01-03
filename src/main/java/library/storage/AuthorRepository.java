package library.storage;

import library.author.Author;
import library.config.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository implements Repository<Author> {

    private final DatabaseConnectionManager connectionManager;

    private static final String INSERT = "INSERT INTO author(first_name, last_name, email) VALUES (?,?,?)";

    private static final String FIND_ALL = "SELECT * FROM author;";

    private static final String DELETE = "DELETE FROM author WHERE id = ?;";

    private static final String SELECT = "SELECT * FROM author WHERE id = ?;";

    private static final String UPDATE = "UPDATE author SET first_name = ?, last_name = ?, email = ? WHERE id = ?";


    public AuthorRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void save(Author entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                Author author = new Author(id, firstName, lastName, email);
                authors.add(author);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authors;
    }

    @Override
    public void delete(Author entity) {
        delete(entity.getId());
    }

    @Override
    public Author findById(long id) {
        Author author = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                author = new Author(id, firstName, lastName, email);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return author;
    }

    @Override
    public void saveAll(List<Author> entities) {
        entities.forEach(this::save);
    }

    @Override
    public void print() {
        findAll().forEach(System.out::println);
    }

    @Override
    public Author update(Author entity) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getEmail());
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
}
