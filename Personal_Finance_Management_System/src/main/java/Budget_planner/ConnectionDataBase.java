
package Budget_planner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionDataBase {
    public static Connection conn=null;
    public static Connection connect() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
            System.out.println("conected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
