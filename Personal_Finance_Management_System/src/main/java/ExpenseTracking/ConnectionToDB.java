
package ExpenseTracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                    + "date text ,"
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
            
            public static void delete(int id, String new_name, String new_category, String new_amount, String new_date, String new_description)throws SQLException
            {
            String delete= "delete from expense where id=?AND name=? AND category=? AND amount=? AND date=? AND description=?";
         try (
              Connection c=DriverManager.getConnection("jdbc:sqlite:expense.db");
              PreparedStatement ps=c.prepareStatement(delete);
              )
         {
         
               ps.setInt(1, id );
        ps.setString(2, new_name ); 
        ps.setString(3, new_category);     
        ps.setString(4, new_amount); 
        ps.setString(5, new_date); 
        ps.setString(6, new_description);
         ps.executeUpdate();
         }
    catch (SQLException ex)
    {          
     System.out.println(ex.getMessage()); 
    }  
            } 
        
          
          
          public static void update(int new_id, String new_name, String new_category, String new_amount, String new_date, String new_description,int old_id ,String old_name, String old_category, String old_amount, String old_date, String old_description) throws SQLException {
    String update = "UPDATE expense SET id=?, name=?, category=?, amount=?, date=?, description=? WHERE id=? AND name=? AND category=? AND amount=? AND date=? AND description=?";
    try (
        Connection c = DriverManager.getConnection("jdbc:sqlite:expense.db");
        PreparedStatement st = c.prepareStatement(update);
    ) {
        st.setInt(1, new_id );
        st.setString(2, new_name ); 
        st.setString(3, new_category);     
        st.setString(4, new_amount); 
        st.setString(5, new_date); 
        st.setString(6, new_description);
        st.setInt(7, old_id);
        st.setString(8, old_name);
        st.setString(9, old_category);
        st.setString(10, old_amount);
        st.setString(11, old_date);
        st.setString(12, old_description);
        st.executeUpdate();
    }



            
            catch(SQLException e) {
            //print a detailed message with the error occured
            System.out.println(e.getMessage());
        }
          
          }
        
}  


        

          
