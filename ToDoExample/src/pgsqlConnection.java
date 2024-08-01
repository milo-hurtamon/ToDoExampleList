import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class pgsqlConnection {
    //Setting variables to connect with the test DB
    Connection pgsqlConn;
    String jdbcURL;
    String dbUser;
    String dbPass;

    public pgsqlConnection() {
        jdbcURL = null;
        dbUser = null;
        dbPass = null;
        pgsqlConn = null;
    }

    //Get the pgsql connection with the DB
    public Connection getPgsqlConnection (String jdbcURL, String dbUser, String dbPass) {
        //Initialize all the variables;
        new pgsqlConnection();

        //Assign the new values to all the strings
        this.jdbcURL = jdbcURL;
        this.dbUser = dbUser;
        this.dbPass = dbPass;

        //Connect to the DB
        try {
            Class.forName("org.postgresql.Driver");
            this.pgsqlConn = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPass);
            System.out.println("Connection stablished");
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            System.err.println(exception.getClass().getName()+": "+exception.getMessage());
        }

        //Returning the DB Connection
        return this.pgsqlConn;
    }

    //Close the connection with the DB
    public void pgsqlCloseConnection (Connection conn) throws SQLException {
        this.pgsqlConn = conn;
        this.pgsqlConn.close();
        System.out.println("DB connection closed");
    }
}
