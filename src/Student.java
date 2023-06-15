import java.io.*;
// import java.util.*;
// import javax.crypto.SecretKey;

// public class Student extends Subject {
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

    @Override
    public void createAccount(String name) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("database/student.txt", true));
        // pw.println(name + ";" + getMatricNo() + ";" + createPassword() + ";CSC186;0");
        try {
            pw.println(name + ";" + getMatricNo() + ";" + passwordManager.createPassword() + ";CSC186;0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.close();
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

    // public String getEncryptedPassword(String password) {
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

    // public String getSecretKey() throws IOException {
    //     // SecretKey encodedKey = passwordEncryption.getSecretKey();
    //     SecretKey encodedKey = passwordManager.getSecretKey();
    //     String secretKey = Base64.getEncoder().encodeToString(encodedKey.getEncoded());
    //     return secretKey;
    // }

    // where output is generated
    @Override
    public String toString() {
        return "Student " + super.toString();
    }

}
