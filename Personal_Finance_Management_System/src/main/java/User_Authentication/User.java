package User_Authentication;

import java.time.LocalDate;

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

}
