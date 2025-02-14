package app.library.config;

import java.util.Map;

public class PropertyConfig {
    private String dbHost;

    private String dbName;

    private String dbUsername;

    private String dbPassword;

    public PropertyConfig() {
        Map<String, String> environmentVariables = System.getenv();

        dbHost = environmentVariables.get("application.db.host");
        dbName = environmentVariables.get("application.db.name");
        dbUsername = environmentVariables.get("application.db.username");
        dbPassword = environmentVariables.get("application.db.password");

        if (dbHost == null || dbHost.isEmpty()) {
            dbHost = System.getProperty("application.db.host");
        }

        if (dbName == null || dbName.isEmpty()) {
            dbName = System.getProperty("application.db.name");
        }

        if (dbUsername == null || dbUsername.isEmpty()) {
            dbUsername = System.getProperty("application.db.username");
        }

        if (dbPassword == null || dbPassword.isEmpty()) {
            dbPassword = System.getProperty("application.db.password");
        }

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
