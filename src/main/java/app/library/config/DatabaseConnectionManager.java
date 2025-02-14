package app.library.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private HikariDataSource ds;

    public DatabaseConnectionManager(PropertyConfig propertyConfig) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(String.format("jdbc:postgresql://%s/%s?currentSchema=library", propertyConfig.getDbHost(), propertyConfig.getDbName()));
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        hikariConfig.setUsername(propertyConfig.getDbUsername());
        hikariConfig.setPassword(propertyConfig.getDbPassword());
        hikariConfig.setMaximumPoolSize(20);
        ds = new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public void close() {
        ds.close();
    }
}
