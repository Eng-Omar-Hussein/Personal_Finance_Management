/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Goal_Setting;
/**
 *
 * @author Mohamed Ahmed
 */
public class Check_Input {
    //Checkes if input is positive and numeric or not
    public static boolean checkNumeric(String input)
    {
        try 
		{ 
			double value = Double.parseDouble(input);
                        if(value<0)
                            return true; //return true if not positive numeric value
                        else return false;//return false if it's positive
		}  
		catch (NumberFormatException e)  
		{
                        //return true if it's not numeric
			return true;
		}
    }
    //Checkes if input is Empty or not
    public static boolean checkEmpty(String input)
    {
            //return true empty
            if(input.equals(""))
                return true;
            else return false;
    }
}