package User_Authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {

    public static boolean Connection() throws SQLException {
        Connection conn = null;
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
            System.out.println("conected");
            return true;
            // Do something with the connection
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean create_table() throws SQLException {
        if (Connection()) {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
            String sql = """
                         CREATE TABLE IF NOT EXISTS users (
                         id INTEGER PRIMARY KEY,
                         username TEXT NOT NULL UNIQUE,
                         password TEXT NOT NULL,
                         email TEXT NOT NULL,
                         full_name TEXT NOT NULL,
                         dob DATE,
                         gender TEXT,
                         Phone TEXT NOT NULL
                         );""";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("table created");
            return true;
        }
        return false;
    }

}
