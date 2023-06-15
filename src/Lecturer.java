import java.io.*;
// import java.util.*;
// import javax.crypto.SecretKey;

public class Lecturer extends User {
    
    // attributes
    private Student[] students;
    private PasswordManager passwordManager = null;
    private String staffId;
    private String password;

    // normal constructor
    public Lecturer(String staffId, String password) throws Exception {
        super(staffId, password);
        this.passwordManager = new PasswordManager(password);
    }

    // public Student[] getStudents() {
    // return students;
    // }

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

    public void setStudents(Student[] students) {
        this.students = new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            this.students[i] = students[i];
        }
    }

    // public String getDecryptedPassword() {
    //     try {
    //         // passwordDecryption = new PasswordDecryption(getPassword(), getSecretKey());
    //         passwordManager = new PasswordManager(getPassword());
    //     } catch (Exception e) {
    //         System.out.println("Error: " + e.getMessage());
    //         System.out.println("Error at line: " + e.getStackTrace()[0].getLineNumber());
    //     }
    //     // return passwordDecryption.getDecryptedPassword();
    //     return passwordManager.getDecryptedPassword();
    // }

    // public String getEncryptedPassword() {
    //     try {
    //         // passwordEncryption = new PasswordEncryption(password);
    //         passwordManager = new PasswordManager(password);
    //     } catch (Exception e) {
    //         System.out.println("Error: " + e.getMessage());
    //         System.out.println("Error at line: " + e.getStackTrace()[0].getLineNumber());
    //     }

    //     // return passwordEncryption.getEncryptedPassword();
    //     return passwordManager.getEncryptedPassword();
    // }

    // method to get secret key from passwordEncryption
    // public String getSecretKey() throws IOException {
    //     // SecretKey encodedKey = passwordEncryption.getSecretKey();
    //     SecretKey encodedKey = passwordManager.getSecretKey();
    //     String secretKey = Base64.getEncoder().encodeToString(encodedKey.getEncoded());
    //     return secretKey;
    // }

    // method to create password
    // public String createPassword() {
    //     try {
    //         // passwordEncryption = new PasswordEncryption(password);
    //         passwordManager = new PasswordManager(password);
    //     } catch (Exception e) {
    //         System.out.println("Error: " + e.getMessage());
    //         System.out.println("Error at line: " + e.getStackTrace()[0].getLineNumber());
    //     }

    //     // SecretKey encodedKey = passwordEncryption.getSecretKey();
    //     SecretKey encodedKey = passwordManager.getSecretKey();
    //     String secretKey = Base64.getEncoder().encodeToString(encodedKey.getEncoded());
    //     // return passwordEncryption.getEncryptedPassword() + ";" + secretKey;
    //     return passwordManager.getEncryptedPassword() + ";" + secretKey;
    // }

    // method to create lecturer account
    // public void createLecturerAcc(String name) throws IOException {
    //     try {
    //         PrintWriter pw = new PrintWriter(new FileWriter("database/lecturer.txt", true));
    //         // pw.println(name + ";" + staffId + ";" + getEncryptedPassword() + ";" +
    //         // getSecretKey());
    //         pw.println(name + ";" + staffId + ";" + createPassword() + "\n");
    //         pw.close();
    //     } catch (Exception e) {
    //         System.out.println("Error: " + e.getMessage());
    //         System.out.println("Error at line: " + e.getStackTrace()[0].getLineNumber());
    //     }
    // }

    // polymorphism
    @Override
    public void createAccount(String name) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("database/lecturer.txt", true));
        try {
            pw.println(name + ";" + staffId + ";" + passwordManager.createPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.close();
    }

    public double calcAverageMarks() {
        double totalMarks = 0;
        for (int i = 0; i < students.length; i++) {
            // totalMarks += students[i].getSubMarks();
        }
        return totalMarks / students.length;
    }

    public double calcTotalMarks() {
        double totalMarks = 0;
        for (int i = 0; i < students.length; i++) {
            // totalMarks += students[i].getSubMarks();
        }
        return totalMarks;
    }

    // dynamic polymorphism
    @Override
    public String toString() {
        return "Staff " + super.toString();
        // output += "Total Marks: " + calcTotalMarks() + "\n";
        // output += "Average Marks: " + calcAverageMarks() + "\n";
        // output += "Student List: \n";
        // for (int i = 0; i < students.length; i++) {
        // output += students[i].generateOutput() + "\n";
        // }
    }

}
