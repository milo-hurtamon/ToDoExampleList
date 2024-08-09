import Core.pgsqlCRUD;
import Core.pgsqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class pgsqlTodoMain {
    // Trying to resolve the DB Connection
    public static String jdbcURL = "jdbc:postgresql://localhost:5432/todo_example";
    public static String username = "pgsql_testrole";
    public static String password = "testingpsswrd";
    public static Integer updResult = 0;

    public static Connection connection = null;

    public static ResultSet queryResult = null;

    // Calling connection object
    public static pgsqlConnection pgsqlConn = new pgsqlConnection();
    // Calling the CRUD object
    public static pgsqlCRUD pgsqlCD = new pgsqlCRUD();

    public static void main(String[] args) {
        //Using the internal functions
        connection = pgsqlConn.getPgsqlConnection(jdbcURL, username, password);

        try {
            // Insertion function
            /*
            pgsqlCD.insertToDoList(connection, "This is the first test", "2024-08-01", false, "");
            pgsqlCD.insertToDoList(connection, "This is the second test", "2024-08-01", true, "");
            pgsqlCD.insertToDoList(connection, "This is the third test", "2024-08-01", false, "");
            pgsqlCD.insertToDoList(connection, "This is the four test", "2024-08-01", true, "");
             */

            // Fetching all the results
            queryResult = pgsqlCD.fetchToDoList(connection, "T");
            while (queryResult.next()) {
                System.out.println("ToDo Description: "+queryResult.getString("description"));
                System.out.println("ToDo Date: "+queryResult.getDate("todo_date"));
                System.out.println("ToDo Completed: "+queryResult.getBoolean("completed"));
                System.out.println("ToDo Observation: "+queryResult.getString("observation"));
                System.out.println();
            }

            // Updating a single row in the table
            updResult = pgsqlCD.updateToDoList(connection, 16, "This is the last test and thats it", "2024-08-09", false, "");
            if (updResult == 200) {
                System.out.println("Everything was updated fine: "+updResult);
            }
            else {
                System.out.println("There is something wrong with the update: "+updResult);
            }

            // DB connection close
            pgsqlConn.pgsqlCloseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}