package app.library.storage;

import app.library.config.DatabaseConnectionManager;
import app.library.exceptions.JournalRepositoryException;
import app.library.exceptions.RepositoryException;
import app.library.model.Journal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JournalRepository implements JournalRepositoryCustom<Journal> {

    private final DatabaseConnectionManager connectionManager;

    private static final String INSERT = "INSERT INTO journal(name, count_pages, number, publication_year) VALUES (?,?,?,?)";

    private static final String FIND_ALL = "SELECT * FROM journal;";

    private static final String DELETE = "DELETE FROM journal WHERE id = ?;";

    private static final String SELECT = "SELECT * FROM journal WHERE id = ?;";

    private static final String UPDATE = "UPDATE journal SET name = ?, count_pages = ?, number = ?, publication_year = ? WHERE id = ?";

    private static final String FIND_BY_NAME_NUMBER_YEAR = "SELECT * FROM journal WHERE name = ? AND publication_year = ? AND number = ?;";

    public JournalRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void save(Journal entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
             preparedStatement.setString(1, entity.getName());
             preparedStatement.setInt(2, entity.getCountPages());
             preparedStatement.setLong(3, entity.getNumber());
             preparedStatement.setInt(4, entity.getPublicationYear());
             preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JournalRepositoryException("Error in Journal save", e);
        }
    }

    @Override
    public List<Journal> findAll() {
        List<Journal> journals = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int countPages = resultSet.getInt("count_pages");
                int number = resultSet.getInt("number");
                int year = resultSet.getInt("publication_year");

                Journal journal = new Journal(id, name, countPages, number, year);
                journals.add(journal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryException("Error in find all journals", e);
        }
        return journals;
    }

    @Override
    public void delete(Journal entity) {
        try(Connection connection = connectionManager.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JournalRepositoryException("Error in journal deletion", e);
        }
    }

    @Override
    public Journal findById(long id) {
        Journal journal = null;
        try (Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                int countPages = resultSet.getInt("count_pages");
                int number = resultSet.getInt("number");
                int year = resultSet.getInt("publication_year");
                journal =  new Journal(id, name, countPages, number, year);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new JournalRepositoryException(String.format("Error in find journal with id %d", id), e);
        }
        return journal;
    }

    @Override
    public void saveAll(List<Journal> entities) {
        entities.forEach(this::save);
    }

    @Override
    public void print() {
        findAll().forEach(System.out::println);
    }

    @Override
    public Journal update(Journal entity) {
        try(Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getCountPages());
            preparedStatement.setLong(3, entity.getNumber());
            preparedStatement.setInt(4, entity.getPublicationYear());
            preparedStatement.setLong(5, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JournalRepositoryException("Error in Journal update", e);
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
            e.printStackTrace();
            throw new JournalRepositoryException(String.format("Error in journal deletion with id %d", id), e);
        }
    }

    @Override
    public List<Journal> findByNameYearNumber(String name, int year, int number) {
        List<Journal> journals = new ArrayList<>();
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_NUMBER_YEAR)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, number);
            ResultSet resultSet  = preparedStatement.executeQuery();
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                journals.add(findById(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JournalRepositoryException(String.format("Error in finding Journal with name %s, year %d and number %d", name, year, number), e);
        }
        return journals;
    }
}
