import java.sql.*;

public class pgsqlCRUD {
    // Variable creation
    private Statement pgsqlStatement;
    private ResultSet pgsqlFetchResult;
    private String sqlStmnt;
    private String queryToFetch;

    // Class constructor
    public pgsqlCRUD() {
        pgsqlStatement = null;
        sqlStmnt = null;
        pgsqlFetchResult = null;
        queryToFetch = null;
    }

    // Function that allow us to insert any new register
    public void insertToDoList(Connection conn, String description, String date, Boolean completed, String observation) throws SQLException {
        this.pgsqlStatement = conn.createStatement();
        this.sqlStmnt = "INSERT INTO todolist (description, todo_date, completed, observation) VALUES('"+description+"','"+date+"','"+completed+"','"+observation+"')";
        this.pgsqlStatement.executeUpdate(this.sqlStmnt);
        this.pgsqlStatement.close();
    }

    // Function that return all the records in the table
    public ResultSet fetchToDoList (Connection conn, String typeOfFetch) throws SQLException {
        this.pgsqlStatement = conn.createStatement();

        // Analize what kind of query we are looking forward
        if (typeOfFetch == "A") {
            this.queryToFetch = "SELECT description, todo_date, completed, observation FROM todolist;";
        } else if (typeOfFetch == "C") {
            this.queryToFetch = "SELECT description, todo_date, completed, observation FROM todolist WHERE completed != false;";
        } else {
            this.queryToFetch = "SELECT description, todo_date, completed, observation FROM todolist WHERE completed = false;";
        }

        this.pgsqlFetchResult = this.pgsqlStatement.executeQuery(this.queryToFetch);
        return this.pgsqlFetchResult;
    }
}
