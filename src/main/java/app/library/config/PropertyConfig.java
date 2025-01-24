package app.library.config;

import jakarta.servlet.ServletContext;

import java.util.Map;

public class PropertyConfig {
    private final String dbHost;

    private final String dbName;

    private final String dbUsername;

    private final String dbPassword;

    public PropertyConfig(ServletContext servletContext) {
        Map<String, String> environmentVariables = System.getenv();
        dbHost = System.getProperty("application.db.host");
        dbName = System.getProperty("application.db.name");
        dbUsername = System.getProperty("application.db.username");
        dbPassword = System.getProperty("application.db.password");

        if (dbHost == null || dbHost.isEmpty()) {
            throw new IllegalArgumentException("dbHost is null or empty. Pass variable dbHost for correct run");
        }

        if (dbName == null || dbName.isEmpty()) {
            throw new IllegalArgumentException("dbHost is null or empty. Pass variable dbName for correct run");
        }

        if (dbUsername == null || dbUsername.isEmpty()) {
            throw new IllegalArgumentException("dbUsername is null or empty. Pass variable dbUsername for correct run");
        }

        if (dbPassword == null || dbPassword.isEmpty()) {
            throw new IllegalArgumentException("dbPassword is null or empty. Pass variable dbPassword for correct run");
        }
    }

    public String getDbHost() {
        return dbHost;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
