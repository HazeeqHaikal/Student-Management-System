import java.io.*;

public class Administrator extends User {

    // attributes
    private PasswordManager passwordManager = null;
    private String staffId;
    private String password;

    // normal constructor
    public Administrator(String staffId, String password) throws Exception {
        super(staffId, password);
        this.passwordManager = new PasswordManager(password);
    }

    // getter and setter methods
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffID() {
        return staffId;
    }

    public void setStaffID(String staffId) {
        this.staffId = staffId;
    }

    // polymorphism
    @Override
    public void createAccount(String name) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("database/administrator.txt", true));
        try {
            pw.println(name + ";" + super.getID() + ";" + passwordManager.createPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.close();
    }


    // where output is generated
    @Override
    public String toString() {
        return "Administrator" + super.toString();
    }
}
