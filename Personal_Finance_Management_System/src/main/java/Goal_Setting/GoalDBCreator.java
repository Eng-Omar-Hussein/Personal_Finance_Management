/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Goal_Setting;
import User_Authentication.Cookies;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Mohamed Ahmed
 */
public class GoalDBCreator {
    //Variable with the id of the current User who is using the program
    static int id;
    // Create a DefaultTableModel to add retrieved data to jTables
    public static DefaultTableModel model, compModel;
    //create connection to the sqlite DataBase
    public static void Connection() throws SQLException {
        id = Cookies.getID();
        ResultSet dataSet = null;
        try (
                Connection c = DriverManager.getConnection("jdbc:sqlite:goals.sqlite");
                Statement st = c.createStatement();
            ){
            System.out.println("Goals DB connected");
            String goalsTable =
            "CREATE TABLE IF NOT EXISTS goals ("
            + "id TEXT NOT NULL,"
            + "goalname TEXT NOT NULL,"
            + "category TEXT NOT NULL,"
            + "targetamount TEXT NOT NULL,"
            + "deadline DATE NOT NULL,"
            + "description TEXT,"
            + "Complete INTEGER);";
            String dataQuery = "SELECT goalname, category, targetamount, deadline, description FROM goals where id="+id+" AND complete IS NULL;";
            st.execute(goalsTable);
            System.out.println("Goals Table connected");
            dataSet = st.executeQuery(dataQuery);
            model = ResultSetIntoTableModel(dataSet);
            dataQuery = "SELECT goalname, category, targetamount, deadline FROM goals where id="+id+" AND complete IS NOT NULL;";
            dataSet = st.executeQuery(dataQuery);
            compModel = ResultSetIntoTableModel(dataSet);
            System.out.println("Data Retrieved Successfully");
        } catch(SQLException e) {
            //print a detailed message with the error occured
            System.out.println(e.getMessage());
        }
        finally{
            dataSet.close();
        }
    }

    //Inser New inserted row in jTable to the Database
    public static void Insert_Data(int id, String goalname, String category, String target, String deadline, String desc)
    {
        String insert = "INSERT INTO goals (id, goalname, category, targetamount, deadline, description) VALUES (?, ?, ?, ?, ? ,?)";
        try (
                Connection c = DriverManager.getConnection("jdbc:sqlite:goals.sqlite");
                PreparedStatement st = c.prepareStatement(insert);
            ){
            st.setInt(1, id);
            st.setString(2, goalname);
            st.setString(3, category);
            st.setString(4, target);
            st.setString(5, deadline);
            st.setString(6, desc);
            st.executeUpdate();

            System.out.println("data inserted successfully");
        } catch(SQLException e) {
            //print a detailed message with the error occured
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Database insertion error", "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Update the Database with the recent changes made to a specific row in jTable
    public static void Update_Data(int id, String old_goalname, String old_category, String old_target, String old_deadline, String old_desc,
                                   String new_goalname, String new_category, String new_target, String new_deadline, String new_desc)
    {
        String Update = "UPDATE goals SET id=?, goalname=?, category=?, targetamount=?, deadline=?, description=?"
                        + " WHERE id=? AND goalname=? AND category=? AND targetamount=? AND deadline=? AND description=? AND complete IS NULL";
        try (
                Connection c = DriverManager.getConnection("jdbc:sqlite:goals.sqlite");
                PreparedStatement st = c.prepareStatement(Update);
            ){
            st.setInt(1, id);
            st.setString(2, new_goalname);
            st.setString(3, new_category);
            st.setString(4, new_target);
            st.setString(5, new_deadline);
            st.setString(6, new_desc);
            st.setInt(7, id);
            st.setString(8, old_goalname);
            st.setString(9, old_category);
            st.setString(10, old_target);
            st.setString(11, old_deadline);
            st.setString(12, old_desc);
            st.executeUpdate();

            System.out.println("data Updated successfully");
        } catch(SQLException e) {
            //print a detailed message with the error occured
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Database Update error", "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void Complete_Data(int id, String goalname, String category, String target, String deadline, String desc)
    {
        String Update = "UPDATE goals SET complete=1"
                        + " WHERE id=? AND goalname=? AND category=? AND targetamount=? AND deadline=? AND description=?;";
        //update complete status of the goal in goals database
        try (
                Connection c = DriverManager.getConnection("jdbc:sqlite:goals.sqlite");
                PreparedStatement st = c.prepareStatement(Update);
            ){
            st.setInt(1, id);
            st.setString(2, goalname);
            st.setString(3, category);
            st.setString(4, target);
            st.setString(5, deadline);
            st.setString(6, desc);
            st.executeUpdate();

            System.out.println("data Updated successfully");
        } catch(SQLException e) {
            //print a detailed message with the error occured
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Database Completion process error", "Warning", JOptionPane.ERROR_MESSAGE);
        }
        String expenseAdding = "INSERT INTO expense (id, name, category, amount, date, description) VALUES (?, ?, ?, ?, ? ,?)";
        //insert the goal's targetamount as an expense in the expense database
        try (
                Connection conn = DriverManager.getConnection("jdbc:sqlite:expense.db");
                PreparedStatement st = conn.prepareStatement(expenseAdding);
            ){
            st.setInt(1, id);
            st.setString(2, goalname);
            st.setString(3, "goal payment");
            st.setString(4, target);
            st.setString(5, deadline);
            st.setString(6, "goal Completion");
            st.executeUpdate();

            System.out.println("goal inserted to expenses successfully");
        } catch(SQLException e) {
            //print a detailed message with the error occured
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "goal expense insertion error", "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Delete the data in the Database of the recent deleted row in the jTable
    public static void Delete_Data(int id, String goalname, String category, String target, String deadline, String desc)
    {
        String delete = "DELETE FROM goals WHERE id=? AND goalname=? AND category=? AND targetamount=? AND deadline=? AND description=? AND complete IS NULL";
        try (
                Connection c = DriverManager.getConnection("jdbc:sqlite:goals.sqlite");
                PreparedStatement st = c.prepareStatement(delete);
            ){
            st.setInt(1, id);
            st.setString(2, goalname);
            st.setString(3, category);
            st.setString(4, target);
            st.setString(5, deadline);
            st.setString(6, desc);
            st.executeUpdate();

            System.out.println("data deleted successfully");
        } catch(SQLException e) {
            //print a detailed message with the error occured
            System.out.println(e.getMessage());
        }
    }

    public static void DeleteComp_Data(int id, String goalname, String category, String target, String deadline)
    {
        String delete = "DELETE FROM goals WHERE id=? AND goalname=? AND category=? AND targetamount=? AND deadline=? AND complete=1";
        try (
                Connection c = DriverManager.getConnection("jdbc:sqlite:goals.sqlite");
                PreparedStatement st = c.prepareStatement(delete);
            ){
            st.setInt(1, id);
            st.setString(2, goalname);
            st.setString(3, category);
            st.setString(4, target);
            st.setString(5, deadline);
            st.executeUpdate();

            System.out.println("data deleted successfully");
        } catch(SQLException e) {
            //print a detailed message with the error occured
            System.out.println(e.getMessage());
        }
    }

    public static void ClearComp_Data(int id)
    {
        String delete = "DELETE FROM goals WHERE id=? AND complete=1";
        try (
                Connection c = DriverManager.getConnection("jdbc:sqlite:goals.sqlite");
                PreparedStatement st = c.prepareStatement(delete);
            ){
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("data Cleared successfully");
        } catch(SQLException e) {
            //print a detailed message with the error occured
            System.out.println(e.getMessage());
        }
    }

    //Converts SQL ResultSet into a DefaultTableModel
    public static DefaultTableModel ResultSetIntoTableModel (ResultSet rs) throws SQLException{
            // create a noneditable DefaultTablemodel
            DefaultTableModel TableModel = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                    return false;
                    }
            };
            // create ResultSetMetaData to retrive data from the ResultSet
            ResultSetMetaData metaData = rs.getMetaData();
            // Add the column names to the model
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
            TableModel.addColumn(metaData.getColumnName(i));
            }
            // Add the rows to the model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                TableModel.addRow(row);
            }
        return TableModel;
    }
}
