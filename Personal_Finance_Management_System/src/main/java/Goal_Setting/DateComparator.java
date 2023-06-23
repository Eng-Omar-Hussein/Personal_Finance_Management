/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Goal_Setting;

import java.util.Date;

/**
 *
 * @author Mohamed Ahmed
 */
public class DateComparator {
    public static boolean is_pastdue(Date date)
    {
        Date currentDate = new Date(); 
        int result = currentDate.compareTo(date);
        if(result <= 0)
            return false;
        else
            return true;
    }
}
