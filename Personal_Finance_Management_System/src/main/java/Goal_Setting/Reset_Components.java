/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Goal_Setting;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author Mohamed Ahmed
 */
public class Reset_Components {
    //reset the components' feilds after clicking Save_Button, Update_Button, or Delete_Button
    public static void Reset_feilds(JTextField goalName, JTextField targetAmount, JTextArea desc, JComboBox category, JDateChooser dateChooser){
        goalName.setText("");
        targetAmount.setText("");
        category.setSelectedIndex(0);
        desc.setText("");
        dateChooser.setDate(new Date());
    }
}
