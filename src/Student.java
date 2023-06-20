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

    public char calcGrade(int marks) {
        char grade = ' ';
        if (marks >= 80) {
            grade = 'A';
        } else if (marks >= 70) {
            grade = 'B';
        } else if (marks >= 60) {
            grade = 'C';
        } else if (marks >= 50) {
            grade = 'D';
        } else if (marks >= 40) {
            grade = 'E';
        } else {
            grade = 'F';
        }
        return grade;
    }

    // polymorphism
    @Override
    public void createAccount(String name, String classIn) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("database/student.txt", true));
        try {
            pw.println(name + ";" + super.getID() + ";" + classIn + ";0;" + passwordManager.createPassword());
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
