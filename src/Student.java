import java.io.*;

public class Student extends User {

    // attributes
    private String matricNo;
    private String password;
    private PasswordManager passwordManager = null;

    // normal constructor
    public Student(String matricNo, String password) throws Exception {
        super(matricNo, password);
        this.passwordManager = new PasswordManager(password);
    }

    // getter and setter methods
    public String getMatricNo() {
        return matricNo;
    }

    public String getPassword() {
        return password;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // polymorphism
    @Override
    public void createAccount(String name) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("database/student.txt", true));
        try {
            pw.println(name + ";" + getMatricNo() + ";" + passwordManager.createPassword() + ";CSC186;0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.close();
    }

    // where output is generated
    @Override
    public String toString() {
        return "Student " + super.toString();
    }

}
