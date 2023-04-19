package User_Authentication;

public class DataValidation {

    public static boolean Email_isvalid(String email) {
        if (email.indexOf("@") < 5) {
            return false;
        }
        if (email.indexOf("@", email.indexOf("@") + 1) != -1) {
            return false;
        }
        String first = email.substring(0, email.indexOf("@"));
        String second = email.substring(email.indexOf("@"), email.length());
        if (hasUpperCase(first)) {
            return false;
        }
        if (!hasDigit(first)) {
            return false;
        }
        if (!hasLowerCase(first)) {
            return false;
        }
        if (hasWhitespace(first)) {
            return false;
        }
        return second.equals("@gmail.com");
    }

    public static boolean Password_isvalid(String password) {
        if (!hasUpperCase(password)) {
            return false;
        }
        if (!hasDigit(password)) {
            return false;
        }
        if (!hasLowerCase(password)) {
            return false;
        }
        return password.length() > 8;
    }

    public static boolean Dateofbirth_isvalid(String day, String month, String year) {
        if (!hasDigit(day)) {
            return false;
        }
        if (!hasDigit(month)) {
            return false;
        }
        if (!hasDigit(year)) {
            return false;
        }
        if (hasUpperCase(day)) {
            return false;
        }
        if (hasUpperCase(month)) {
            return false;
        }
        if (hasUpperCase(year)) {
            return false;
        }
        if (hasLowerCase(day)) {
            return false;
        }
        if (hasLowerCase(month)) {
            return false;
        }
        return !hasLowerCase(year);
    }

    public static boolean Phone_isvalid(String phone) {
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        if(phone.length()<2) return false;
        String s=phone.substring(0, 2);
        return phone.length() == 11 && s.equals("01") ;
    }

    private static boolean hasUpperCase(String first) {
        for (int i = 0; i < first.length(); i++) {
            if (Character.isUpperCase(first.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasDigit(String first) {
        for (int i = 0; i < first.length(); i++) {
            if (Character.isDigit(first.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasWhitespace(String first) {
        for (int i = 0; i < first.length(); i++) {
            if (Character.isWhitespace(first.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasLowerCase(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
