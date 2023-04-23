package User_Authentication;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

public class User {

    private int ID;
    private String Username;
    private String Password;
    private String Email;
    private String Full_name;
    private LocalDate Dob;
    private String Gender;
    private String Phone;

    public User(int ID, String Username, String Password, String Email, String Full_name, LocalDate Dob, String Gender, String Phone) {
        this.ID = ID;
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.Full_name = Full_name;
        this.Dob = Dob;
        this.Gender = Gender;
        this.Phone = Phone;
    }

    public User() {
        this.ID = 0;
        this.Username = null;
        this.Password = null;
        this.Email = null;
        this.Full_name = null;
        this.Dob = null;
        this.Gender = null;
        this.Phone = null;
    }

    public User(User user) {
        this.ID = user.ID;
        this.Username = user.Username;
        this.Password = user.Password;
        this.Email = user.Email;
        this.Full_name = user.Full_name;
        this.Dob = user.Dob;
        this.Gender = user.Gender;
        this.Phone = user.Phone;
    }

    public User(String Username, String Password, String Email, String Full_name, String Dob, String Gender, String Phone) {
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.Full_name = Full_name;
        this.Dob = LocalDate.parse(Dob);
        this.Gender = Gender;
        this.Phone = Phone;
    }

    public String getFull_name() {
        return Full_name;
    }

    public void setFull_name(String Full_name) {
        this.Full_name = Full_name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public LocalDate getDob() {
        return Dob;
    }

    public void setDob(LocalDate Dob) {
        this.Dob = Dob;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    @Override
    public String toString() {
        return "User{" + "ID=" + ID + ", Username=" + Username + ", Password=" + Password + ", Email=" + Email + ", Full_name=" + Full_name + ", Phone=" + Phone + ", Dob=" + Dob + ", Gender=" + Gender + '}';
    }

    public static ArrayList<User> getAllUsers_db() throws SQLException {
        ArrayList<User> userList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
            PreparedStatement stmt = conn.prepareStatement("SELECT * from users ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String full_name = rs.getString("full_name");
                LocalDate dob = LocalDate.parse(rs.getString("dob"));
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");

                User user = new User(id, username, password, email, full_name, dob, gender, phone);
                userList.add(user);
            }
        } catch (SQLException e) {
            // Handle the exception here
            e.printStackTrace();
            throw e;
        }

        return userList;
    }

    public static void setUser_db(String username, String password, String email, String full_name, String dob, String gender, String phone) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");

        String sql = "INSERT INTO users(username, password, email, full_name, dob, gender, phone)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";

        stmt = conn.prepareStatement(sql);

        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.setString(3, email);
        stmt.setString(4, full_name);
        stmt.setString(5, dob);
        stmt.setString(6, gender);
        stmt.setString(7, phone);
        stmt.executeUpdate();

        System.out.println("Data inserted into user table successfully");

    }

    public static void deleteUser_db(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
        String sql = "delete from users where id=?";

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        System.out.println("user deleted");

    }

    public static void UpdateEmail_db(String newmail) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
        String sql = "update users set email=?  where id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, newmail);
        stmt.setInt(2, Cookies.getID());
        stmt.executeUpdate();
        System.out.println("Email Updated");
    }

    public static void UpdatepaPassword_db(String newpass) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
        String sql = "update users set password=?  where id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, newpass);
        stmt.setInt(2, Cookies.getID());
        stmt.executeUpdate();
        System.out.println("Password Updated");
    }

    public static void UpdatepaPhone_db(String newphone) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = DriverManager.getConnection("jdbc:sqlite:Personal_Information.sqlite");
        String sql = "update users set phone=?  where id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, newphone);
        stmt.setInt(2, Cookies.getID());
        stmt.executeUpdate();
        System.out.println("Phone Updated");
    }
    
    public static void sendmail(String to) {
        String email = "personalfinanc1@gmail.com";
        String password = "qhzsxcwzasatxost";
        // Set up the properties for the SMTP server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Set up the session
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            // Set up the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email, "PFMS"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Your PFMS Password");
            message.setText("Hi "+Cookies.getUsername()+",\nYour Password: "+Cookies.getPassword());
            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
