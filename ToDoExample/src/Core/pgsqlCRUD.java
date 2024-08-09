package Core;

import java.sql.*;

/*
TODO:
  Create the interfaces to separate the whole CRUD into detailed functionalities as SOLID propose
 */

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
        this.sqlStmnt = "INSERT INTO todolist (description, todo_date, completed, observation) VALUES('"+description+"','"+date+"',"+completed+",'"+observation+"')";
        this.pgsqlStatement.executeUpdate(this.sqlStmnt);
        this.pgsqlStatement.close();
    }

    // Function that return all the records in the table
    public ResultSet fetchToDoList (Connection conn, String typeOfFetch) throws SQLException {
        this.pgsqlStatement = conn.createStatement();

        // Analize what kind of query we are looking forward
        if (typeOfFetch == "A") {
            this.queryToFetch = "SELECT * FROM todolist;";
        } else if (typeOfFetch == "C") {
            this.queryToFetch = "SELECT * FROM todolist WHERE completed != false;";
        } else {
            this.queryToFetch = "SELECT * FROM todolist WHERE completed = false;";
        }

        this.pgsqlFetchResult = this.pgsqlStatement.executeQuery(this.queryToFetch);
        return this.pgsqlFetchResult;
    }

    /*
    TODO:
        Verify that the register exist first before try to update because the DB will response with 0 is the structure
        is fine and will not find any kind of problem
     */
    // Function that will update an existing record
    public Integer updateToDoList (Connection conn, Integer todoID, String description, String date, Boolean completed, String observation) {
        // Something to do here
        try {
            this.pgsqlStatement = conn.createStatement();
            this.sqlStmnt = "UPDATE todolist SET description = '" + description + "', todo_date = '" + date + "', completed = " + completed + ", observation = '" + observation + "' WHERE todoid = " + todoID + ";";
            this.pgsqlStatement.executeUpdate(this.sqlStmnt);
            this.pgsqlStatement.close();
            return 200;
        } catch (SQLException eSQL) {
            System.err.println(eSQL.getClass().getName()+": "+eSQL.getMessage());
            return 500;
        }
    }
}
