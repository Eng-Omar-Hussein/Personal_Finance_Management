
package User_Authentication;

import java.time.LocalDate;

public class Cookies {
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
        Cookies.Cookie = Cookie;
    }
}
