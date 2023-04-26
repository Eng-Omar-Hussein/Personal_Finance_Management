/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package incometracking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ahmed
 */
public class DataBaseIncome {

    public static boolean Connection() throws SQLException {
        Connection conn = null;
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:sqlite:Income_Data.sqlite");
            System.out.println("conected");
            return true;
            // Do something with the connection
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBaseIncome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean create_table() throws SQLException {
        if (Connection()) {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Income_Data.sqlite");
            String sql = """
                         CREATE TABLE IF NOT EXISTS Income (
                         id INTEGER NOT NULL,
                         category TEXT NOT NULL,
                         amount INTEGER NOT NULL,
                         description TEXT NOT NULL,
                         date TEXT NOT NULL
                         );""";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("table created");
            return true;
        }
        return false;
    }
    
}
