/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Budget_Planner_New;

import Budget_planner.*;

/**
 *
 * @author anass
 */
public class BudgetData {
    private String name;
    private int planned;
    private int actual;
    private int diff;
    private String Date;
    private String discription;

    public BudgetData(String name, int planned, int actual, int diff, String Date, String discription) {
        this.name = name;
        this.planned = planned;
        this.actual = actual;
        this.diff = diff;
        this.Date = Date;
        this.discription = discription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlanned() {
        return planned;
    }

    public void setPlanned(int planned) {
        this.planned = planned;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    
    
}
