import java.sql.Connection;
import java.sql.SQLException;

public class pgsqlConnectionTest {
    // Trying to resolve the DB Connection
    public static String jdbcURL = "jdbc:postgresql://localhost:5432/todo_example";
    public static String username = "pgsql_testrole";
    public static String password = "testingpsswrd";

    public static Connection connection = null;

    // Calling connection object
    public static pgsqlConnection pgsqlConn = new pgsqlConnection();
    // Calling the CRUD object
    public static pgsqlCRUD pgsqlCD = new pgsqlCRUD();

    public static void main(String[] args) {
        //Using the internal functions
        connection = pgsqlConn.getPgsqlConnection(jdbcURL, username, password);

        try {
            // Insertion function
            pgsqlCD.insertToDoList(connection, "This is the first test", "2024-08-01", false, "");

            // DB connection close
            pgsqlConn.pgsqlCloseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}