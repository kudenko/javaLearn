package app.library.config;

import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class HibernateConfig {

    private static final Logger logger = LoggerFactory.getLogger(HibernateConfig.class);

    public static Configuration createConfiguration(PropertyConfig propertyConfig) {
        Configuration configuration = new Configuration();


        logger.info("Hibernate config initialization start");

        Properties settings = new Properties();
        settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        settings.put("hibernate.connection.url", String.format("jdbc:postgresql://%s/%s", propertyConfig.getDbHost(), propertyConfig.getDbName()));
        settings.put("hibernate.connection.username", propertyConfig.getDbUsername());
        settings.put("hibernate.connection.password", propertyConfig.getDbPassword());
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.enable_lazy_load_no_trans", "true");

        configuration.setProperties(settings);

        logger.info("Hibernate config initialization end");

        return configuration;
    }
}
