package library.storage;

import library.config.DatabaseConnectionManager;
import library.model.Journal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JournalRepository implements Repository<Journal> {

    private final DatabaseConnectionManager connectionManager;

    private static final String INSERT = "INSERT INTO journal(name, count_pages, number, publication_year) VALUES (?,?,?,?)";

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
        } catch (SQLException ex) {
            //log
            //TODO change to repository exception
            throw new RuntimeException("");
        }
    }

    @Override
    public List<Journal> findAll() {
        return null;
    }

    @Override
    public void delete(Journal entity) {

    }

    @Override
    public Journal findById(long id) {
        return null;
    }

    @Override
    public void saveAll(List<Journal> entities) {

    }

    @Override
    public void print() {

    }

    @Override
    public Journal update(Journal entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
