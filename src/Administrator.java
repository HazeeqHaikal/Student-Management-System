import java.io.*;
import java.util.*;

import javax.crypto.SecretKey;

public class Administrator extends User{
    // attributes
    private Student[] students;
    // private PasswordDecryption passwordDecryption = null;
    // private PasswordEncryption passwordEncryption = null;
    private PasswordManager passwordManager = null;
    // private String name;
    private String staffId;
    private String password;

    // normal constructor
    public Administrator(String staffId, String password) {
        super(staffId, password);
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

    public String getDecryptedPassword() {
        try {
            // passwordDecryption = new PasswordDecryption(getPassword(), getSecretKey());
            passwordManager = new PasswordManager(getPassword());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Error at line: " + e.getStackTrace()[0].getLineNumber());
        }
        // return passwordDecryption.getDecryptedPassword();
        return passwordManager.getDecryptedPassword();
    }

    public void setStudents(Student[] students) {
        this.students = new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            this.students[i] = students[i];
        }
    }

    public String getSecretKey() throws IOException {
        // SecretKey encodedKey = passwordEncryption.getSecretKey();
        SecretKey encodedKey = passwordManager.getSecretKey();
        String secretKey = Base64.getEncoder().encodeToString(encodedKey.getEncoded());
        return secretKey;
    }

    public String getEncryptedPassword() {
        try {
            // passwordEncryption = new PasswordEncryption(password);
            passwordManager = new PasswordManager(password);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Error at line: " + e.getStackTrace()[0].getLineNumber());
        }

        // return passwordEncryption.getEncryptedPassword();
        return passwordManager.getEncryptedPassword();
    }

    // processor
    public String createPassword() {
        try {
            // passwordEncryption = new PasswordEncryption(password);
            passwordManager = new PasswordManager(password);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Error at line: " + e.getStackTrace()[0].getLineNumber());
        }

        // SecretKey encodedKey = passwordEncryption.getSecretKey();
        SecretKey encodedKey = passwordManager.getSecretKey();
        String secretKey = Base64.getEncoder().encodeToString(encodedKey.getEncoded());
        // return passwordEncryption.getEncryptedPassword() + ";" + secretKey;
        return passwordManager.getEncryptedPassword() + ";" + secretKey;
    }

    public void createAdminAcc(String name) throws IOException {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("database/administrator.txt", true));
            // pw.println(name + ";" + staffId + ";" + getEncryptedPassword() + ";" +
            // getSecretKey());
            pw.println(name + ";" + staffId + ";" + createPassword() + "\n");
            pw.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Error at line: " + e.getStackTrace()[0].getLineNumber());
        }
    }

    // where output is generated
    public String toString() {
        String output = "";
        output += "Staff ID: " + staffId + "\n";
        output += "Password: " + password;
        // output += "Student List: \n";
        // for (int i = 0; i < students.length; i++) {
        // output += students[i].generateOutput() + "\n";
        // }
        return output;
    }

}
