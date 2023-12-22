import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public  class MyAbstractClass {
    public class DatabaseConnection {
        private static Connection connection;

        public static Connection getConnection() throws SQLException {
            if (connection == null) {
                String jdbcURL = "jdbc:mysql://localhost:3306/cua_hang";
                String username = "root";
                String password = "choinhanh12";
                connection = DriverManager.getConnection(jdbcURL, username, password);
            }
            return connection;
        }

        public static void closeConnection() throws SQLException {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
