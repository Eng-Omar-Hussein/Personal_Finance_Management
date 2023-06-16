
package ExpenseTracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class ConnectionToDB {
    static int id;
    
    public static DefaultTableModel tablemodel;
    public static void Connection() {
        try (
            Connection c = DriverManager.getConnection("jdbc:sqlite:expense.db");
            Statement st=c.createStatement();
             ){
            System.out.println("connected to data base");
            
            String expense="create table if not exists expense("
                    + "id int not null,"
                    + "name text not null,"
                    +"category text not null,"
                    + "amount text not null,"
                    + "date DATE not null,"
                    + "description text)";
            st.execute(expense);
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
            public static void Insert_Data(int id, String name , String category,String amount , String date,String description)throws SQLException
            {
                    String insert = "INSERT INTO expense VALUES (?, ?, ?, ?, ? ,?)";
                      try (
                           Connection c=DriverManager.getConnection("jdbc:sqlite:expense.db");
                            PreparedStatement ps=c.prepareStatement(insert);
                            )
                          
                      {
                      ps.setInt(1, id);
                      ps.setString(2, name);
                    ps.setString(3, category);
                    ps.setString(4, amount);
                    ps.setString(5, date);
                    ps.setString(6, description);
                    ps.executeUpdate();
                    System.out.println("inserted succesflly");
                      }   
                      catch (SQLException ex) {          
            System.out.println(ex.getMessage()); 
            }     
            }
            
            public static void delete(int id)throws SQLException
            {
            String delete= "delete from expense where id=?";
         try (
              Connection c=DriverManager.getConnection("jdbc:sqlite:expense.db");
              PreparedStatement ps=c.prepareStatement(delete);
              )
         {
         
            ps.setInt(1, id);
         ps.executeUpdate();
         }
    catch (SQLException ex)
    {          
     System.out.println(ex.getMessage()); 
    }  
}  


}            

          
