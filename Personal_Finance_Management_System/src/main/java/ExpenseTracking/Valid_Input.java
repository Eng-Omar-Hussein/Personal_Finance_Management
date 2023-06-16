package ExpenseTracking;
import ExpenseTracking.Expensetracking;
import javax.swing.JOptionPane;

public class Valid_Input {
public static boolean valid_name(String name){
    
            for (int i = 0; i < name.length(); i++) 
            {
            if(Character.isDigit(name.charAt(i)))
                return false;
            }
    return true;
    
}
public static boolean valid_amount(String amount){
    
            for (int i = 0; i < amount.length(); i++) 
            {
            if(!amount.contains("$"))
                return false;
            }
    return true;
    
}

}