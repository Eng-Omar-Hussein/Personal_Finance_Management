package incometracking;

import User_Authentication.Cookies;
import User_Authentication.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class IncomeData {
    private static final int ID = Cookies.getID();
    private String Category;
    private int Amount;
    private String Description;
    private String Date;

    private IncomeData( String category, int amount, String date) {
        this.Category=category;
        this.Amount=amount;
        this.Date=date;
    }
    public int getID() {
        return ID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    public static ArrayList<IncomeData> getAllUsers_db(int id) throws SQLException {
        ArrayList<IncomeData> List = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Income_Data.sqlite");
            PreparedStatement stmt = null;
            String sql = "SELECT * from Income where id =?" ;
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String category = rs.getString("category");
                int amount = rs.getInt("amount");
                String date = rs.getString("date");

                IncomeData user = new IncomeData(category, amount, date);
                List.add(user);
            }
        } catch (SQLException e) {
            // Handle the exception here
            e.printStackTrace();
            throw e;
        }

        return List;
    }

    public static void setIncome_Data(String category,int amount , String description, String date) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        conn = DriverManager.getConnection("jdbc:sqlite:Income_Data.sqlite");

        String sql = "INSERT INTO Income(id, category, amount, description, date)"
                + "VALUES(?, ?, ?, ?, ?)";

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ID);
        stmt.setString(2, category);
        stmt.setInt(3, amount);
        stmt.setString(4, description);
        stmt.setString(5, date);
        stmt.executeUpdate();

        System.out.println("Data inserted into user table successfully");

    }

    public static void deletIncome(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = DriverManager.getConnection("jdbc:sqlite:Income_Data.sqlite");
        String sql = "delete from Income where id=?";

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        System.out.println("Income deleted");

    }

}
