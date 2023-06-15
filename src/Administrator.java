import java.io.*;
// import java.util.*;
// import javax.crypto.SecretKey;

public class Administrator extends User{
    
    // attributes
    private Student[] students;
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

    public void setStudents(Student[] students) {
        this.students = new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            this.students[i] = students[i];
        }
    }

    // public String getSecretKey() throws IOException {
    //     // SecretKey encodedKey = passwordEncryption.getSecretKey();
    //     SecretKey encodedKey = passwordManager.getSecretKey();
    //     String secretKey = Base64.getEncoder().encodeToString(encodedKey.getEncoded());
    //     return secretKey;
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

    // processor

    // polymorphism
    @Override
    public void createAccount(String name) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("database/administrator.txt", true));
        try {
            pw.println(name + ";" + staffId + ";" +  passwordManager.createPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.close();
    }

    // where output is generated
    @Override
    public String toString() {
        return "Administrator" + super.toString();
        // output += "Student List: \n";
        // for (int i = 0; i < students.length; i++) {
        // output += students[i].generateOutput() + "\n";
        // }
    }


}
