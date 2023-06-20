import java.io.IOException;

public abstract class User {
    // attributes
    private String ID;
    private String password;

    // normal constructor
    public User(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    // getter and setter methods
    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract void createAccount(String name) throws IOException;

    // tostring
    public String toString() {
        return "ID: " + ID + "\nPassword: " + password;
    }

}