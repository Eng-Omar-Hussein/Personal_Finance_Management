package com.mycompany.personal_finance_management_system;

import User_Authentication.Login_Page;
import User_Authentication.User;
import java.time.LocalDate;

public class Personal_Finance_Management_System {

    private static User Cookie;

    public static User getCookie() {
        return Cookie;
    }

    public static String getEmail() {
        return Cookie.getEmail();
    }

    public static String getFull_name() {
        return Cookie.getFull_name();
    }

    public static String getGender() {
        return Cookie.getGender();
    }

    public static LocalDate getDob() {
        return Cookie.getDob();
    }

    public static String getPassword() {
        return Cookie.getPassword();
    }

    public static String getPhone() {
        return Cookie.getPhone();
    }

    public static int getID() {
        return Cookie.getID();
    }

    public static String getUsername() {
        return Cookie.getUsername();
    }

    public static void setCookie(User Cookie) {
        Personal_Finance_Management_System.Cookie = Cookie;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Login_Page obj = new Login_Page();
    }
}
