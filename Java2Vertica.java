import java.sql.*;
import java.util.Properties;
public class Java2Vertica {
        public static void main(String[] args) {
                Connection conn;
                try {
                        conn  = DriverManager.getConnection(
                                "jdbc:vertica://127.0.0.1:5433/db",
                                "dbadmin", "1804");
                        System.out.println("Connected");
                        conn.close();
                } catch (SQLTransientConnectionException connException) {
                        System.out.print("Network connection issue: ");
                        System.out.print(connException.getMessage());
                        System.out.println(" Try again later!");
                        return;
                } catch (SQLInvalidAuthorizationSpecException authException) {
                        // Wrong log in credentials
                        System.out.print("Could not log into database: ");
                        System.out.print(authException.getMessage());
                        System.out.println("Check the login credentials and try again.");
                        return;
                } catch (SQLException e) {
                        // Catch-all for other exceptions
                        e.printStackTrace();
                }
        }
}
