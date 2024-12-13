import jdbc.config.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseConnectionManager dbcm = new DatabaseConnectionManager("localhost", "learning", "librarian", "libra");
        try {
            Connection connection = dbcm.getConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Dispatcher.initApp();
    }
}
