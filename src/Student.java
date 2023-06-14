import java.io.*;
import java.util.*;

import javax.crypto.SecretKey;

// public class Student extends Subject {
public class Student {
    // attributes
    // private String[] studentInfo = new String[6];
    // private PasswordDecryption passwordDecryption = null;
    // private PasswordEncryption passwordEncryption = null;
    private PasswordManager passwordManager = null;
    private String matricNo;
    private String password;

    // constructor
    // public Student(String matricNo, String password, double subMarks) {
    // super(subMarks);
    public Student(String matricNo, String password) {
        this.matricNo = matricNo;
        this.password = password;
    }

    // public Student(String[] studentInfo, double subMarks) throws Exception {
    // super(subMarks);
    // this.studentInfo = studentInfo;
    // }

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

    // public double getSubMarks() {
    // return super.getSubMarks();
    // }

    // public void setSubMarks(double subMarks) {
    // super.setSubMarks(subMarks);
    // }

    public void createStudentAcc(String name) throws IOException {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("database/student.txt", true));
            pw.println(name + ";" + matricNo + ";" + createPassword() + ";CSC186;0");
            pw.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Error at line: " + e.getStackTrace()[0].getLineNumber());
        }
    }

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

    public String getEncryptedPassword(String password) {
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

    public String getSecretKey() throws IOException {
        // SecretKey encodedKey = passwordEncryption.getSecretKey();
        SecretKey encodedKey = passwordManager.getSecretKey();
        String secretKey = Base64.getEncoder().encodeToString(encodedKey.getEncoded());
        return secretKey;
    }

    // getter and setter
    // public String getName() {
    // return studentInfo[0];
    // }

    // public void setName(String[] studentInfo) {
    // this.studentInfo[0] = studentInfo[0];
    // }

    // public String getMatricNo() {
    // return studentInfo[1];
    // }

    // public void setMatricNo(String[] studentInfo) {
    // this.studentInfo[1] = studentInfo[1];
    // }

    // public double getSubMarks() {
    // return super.getSubMarks();
    // }

    // public void setSubMarks(double subMarks) {
    // super.setSubMarks(subMarks);
    // }

    // public String[] getStudentInfo() {
    // return studentInfo;
    // }

    // public void setStudentInfo(String[] studentInfo) {
    // this.studentInfo = studentInfo;
    // }

    // public PasswordEncryption getPasswordEncryption() {
    // return passwordEncryption;
    // }

    // public void setPasswordEncryption(PasswordEncryption passwordEncryption) {
    // this.passwordEncryption = passwordEncryption;
    // }

    // public String getPassword() {
    // try{
    // passwordDecryption = new PasswordDecryption(studentInfo[2], studentInfo[3]);
    // } catch (Exception e) {
    // System.out.println("Error: " + e.getMessage());
    // System.out.println("Error at line: " + e.getStackTrace()[0].getLineNumber());
    // }
    // return passwordDecryption.getDecryptedPassword();
    // }

    // public void setPassword(String password) throws Exception {
    // this.passwordEncryption.setPassword(password);
    // }

    // processor to write data for current student to database/student.txt
    // public void writeStudentData() throws Exception {
    // try {
    // BufferedWriter writer = new BufferedWriter(new FileWriter("database/student.txt",
    // true));
    // writer.write(accessStudentFile()[0] + ";" + matricNo + ";" + password + ";" +
    // + "\n");
    // writer.close();
    // } catch (Exception e) {
    // System.out.println("Error: " + e.getMessage());
    // System.out.println("Error at line: " + e.getStackTrace()[0].getLineNumber());
    // }
    // }

    // where output is generated
    public String toString() {
        String output = "";
        // try {
        // output += "Name: " + accessStudentFile()[0] + "\n";
        // } catch (Exception e) {
        // System.out.println("Error: " + e.getMessage());
        // System.out.println("Error at line: " + e.getStackTrace()[0].getLineNumber());
        // }
        output += "Matric No: " + matricNo + "\n";
        output += "Password: " + password + "\n";
        // output += "Subject Marks: " + getSubMarks() + "\n";
        return output;
    }

}
