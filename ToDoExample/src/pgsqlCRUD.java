import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class pgsqlCRUD {
    // Variable creation
    private Statement pgsqlStatement;
    private String sqlStmnt;

    // Class constructor
    public pgsqlCRUD() {
        pgsqlStatement = null;
        sqlStmnt = null;
    }

    // Function that allow us to insert any new register
    public void insertToDoList(Connection conn, String description, String date, Boolean completed, String observation) throws SQLException {
        this.pgsqlStatement = conn.createStatement();
        this.sqlStmnt = "INSERT INTO todolist (description, todo_date, completed, observation) VALUES('"+description+"','"+date+"','"+completed+"','"+observation+"')";
        this.pgsqlStatement.executeUpdate(this.sqlStmnt);
        this.pgsqlStatement.close();
    }
}
