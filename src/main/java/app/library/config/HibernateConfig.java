package app.library.config;

import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateConfig {
    public static Configuration createConfiguration(PropertyConfig propertyConfig) {
        Configuration configuration = new Configuration();

        Properties settings = new Properties();
        settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        settings.put("hibernate.connection.url", String.format("jdbc:postgresql://%s/%s", propertyConfig.getDbHost(), propertyConfig.getDbName()));
        settings.put("hibernate.connection.username", propertyConfig.getDbUsername());
        settings.put("hibernate.connection.password", propertyConfig.getDbPassword());
        settings.put("hibernate.show_sql", "true");

        configuration.setProperties(settings);

        return configuration;
    }
}
