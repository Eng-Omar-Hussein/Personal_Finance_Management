package User_Authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public static ArrayList<User> getAllUsers_db() throws SQLException {
        ArrayList<User> userList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
            PreparedStatement stmt = conn.prepareStatement("SELECT * from users ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String full_name = rs.getString("full_name");
                LocalDate dob = LocalDate.parse(rs.getString("dob"));
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");

                User user = new User(id, username, password, email, full_name, dob, gender, phone);
                userList.add(user);
            }
        } catch (SQLException e) {
            // Handle the exception here
            e.printStackTrace();
            throw e;
        }

        return userList;
    }

    public static void setUser_db(String username, String password, String email, String full_name, String dob, String gender, String phone) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");

        String sql = "INSERT INTO users(username, password, email, full_name, dob, gender, phone)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";

        stmt = conn.prepareStatement(sql);

        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.setString(3, email);
        stmt.setString(4, full_name);
        stmt.setString(5, dob);
        stmt.setString(6, gender);
        stmt.setString(7, phone);
        stmt.executeUpdate();

        System.out.println("Data inserted into user table successfully");

    }

    public static void deleteUser_db(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
        String sql = "delete from users where id=?";

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        System.out.println("user deleted");

    }

    public static void UpdateEmail_db(String newmail) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
        String sql = "update users set email=?  where id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, newmail);
        stmt.setInt(2, Cookies.getID());
        stmt.executeUpdate();
        System.out.println("Email Updated");
    }

    public static void UpdatepaPassword_db(String newpass) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
        String sql = "update users set password=?  where id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, newpass);
        stmt.setInt(2, Cookies.getID());
        stmt.executeUpdate();
        System.out.println("Password Updated");
    }

    public static void UpdatepaPhone_db(String newphone) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
        String sql = "update users set phone=?  where id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, newphone);
        stmt.setInt(2, Cookies.getID());
        stmt.executeUpdate();
        System.out.println("Phone Updated");
    }

    public static double getTotalIncome_db(int id) throws SQLException {
        double total = 0;
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Income_Data.sqlite");
        PreparedStatement stmt = conn.prepareStatement("SELECT amount from Income where id=?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            total += rs.getInt("amount");
        }

        return total;
    }

    public static double getTotalExpense_db(int id) throws SQLException {
        double total = 0;
        Connection conn = DriverManager.getConnection("jdbc:sqlite:expense.db");
        PreparedStatement stmt = conn.prepareStatement("SELECT amount from expense where id=?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            total += Double.parseDouble(rs.getString("amount"));
        }

        return total;
    }

}
