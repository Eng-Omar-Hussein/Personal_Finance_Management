/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpenseTracking;

import User_Authentication.Cookies;

/**
 *
 * @author asmaa
 */
public class expense_varibles {

  private int id=Cookies.getID();
   private String name;
   private String amount;
   private String category;
   private String date;
   private String descriptipon;
    public expense_varibles() {
    }

    public expense_varibles(int id,String name, String category, String amount , String date, String descriptipon) {
        this.id=id;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.descriptipon = descriptipon;
    }

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
       this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescriptipon() {
        return descriptipon;
    }

    public void setDescriptipon(String descriptipon) {
        this.descriptipon = descriptipon;
    }

   
   
   
}
