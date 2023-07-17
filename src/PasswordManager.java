import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.*;

public class PasswordManager {

    // salt for key derivation
    private static final byte[] SALT = "12345678".getBytes();
    // iteration count for key derivation
    private static final int ITERATION_COUNT = 65536;
    // key length for AES
    private static final int KEY_LENGTH = 256;
    // initialization vector for AES
    private static final byte[] IV = "1234567890abcdef".getBytes();
    // secret key algorithm
    private static final String SECRET_KEY_ALGORITHM = "PBKDF2WithHmacSHA512";
    // cipher algorithm
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    // password to encrypt and decrypt
    private String password;
    // secret key for encryption and decryption
    private SecretKey secretKey;
    // encrypted password
    private String encryptedPassword;
    // decrypted password
    private String decryptedPassword;
    // private Student student;

    // normal constructor
    public PasswordManager(String password) throws Exception {
        this.password = password;
        this.secretKey = generateSecretKey(password);
        this.encryptedPassword = encryptPassword(password, secretKey);
        this.decryptedPassword = decryptPassword(encryptedPassword, secretKey);
    }

    // getter and setter methods
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        this.password = password;
        this.secretKey = generateSecretKey(password);
        this.encryptedPassword = encryptPassword(password, secretKey);
        this.decryptedPassword = decryptPassword(encryptedPassword, secretKey);
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getDecryptedPassword() {
        return decryptedPassword;
    }

    // method to generate secret key that will be used to encrypt password
    private static SecretKey generateSecretKey(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray(), SALT, ITERATION_COUNT, KEY_LENGTH);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        return secretKeySpec;
    }

    public SecretKeySpec getSecretKey() {
        return (SecretKeySpec) secretKey;
    }

    // method to verify the existence of the account
    public boolean findAccount(String matricNo) throws Exception {
        BufferedReader lecturer = new BufferedReader(new FileReader("database/lecturer.txt"));
        BufferedReader student = new BufferedReader(new FileReader("database/student.txt"));
        BufferedReader admin = new BufferedReader(new FileReader("database/administrator.txt"));
        String studentLine = student.readLine();
        String lecturerLine = lecturer.readLine();
        String adminLine = admin.readLine();
        while (studentLine != null) {
            String[] studentDetails = studentLine.split(";");
            if (studentDetails[1].equals(matricNo)) {
                student.close();
                lecturer.close();
                admin.close();
                return true;
            }
            studentLine = student.readLine();
        }
        while (lecturerLine != null) {
            String[] lecturerDetails = lecturerLine.split(";");
            if (lecturerDetails[1].equals(matricNo)) {
                student.close();
                lecturer.close();
                admin.close();
                return true;
            }
            lecturerLine = lecturer.readLine();
        }
        while (adminLine != null) {
            String[] adminDetails = adminLine.split(";");
            if (adminDetails[1].equals(matricNo)) {
                student.close();
                lecturer.close();
                admin.close();
                return true;
            }
            adminLine = admin.readLine();
        }
        student.close();
        lecturer.close();
        admin.close();
        return false;
    }

    // method to get the password of the student
    public char getTypeAccount(String matricNo) throws Exception {
        BufferedReader lecturer = new BufferedReader(new FileReader("database/lecturer.txt"));
        BufferedReader student = new BufferedReader(new FileReader("database/student.txt"));
        BufferedReader admin = new BufferedReader(new FileReader("database/administrator.txt"));
        String studentLine = student.readLine();
        String lecturerLine = lecturer.readLine();
        String adminLine = admin.readLine();
        char type = ' ';
        while (studentLine != null) {
            String[] studentDetails = studentLine.split(";");
            if (studentDetails[1].equals(matricNo)) {
                student.close();
                lecturer.close();
                admin.close();
                type = 'S';
                return type;
            }
            studentLine = student.readLine();
        }
        while (lecturerLine != null) {
            String[] lecturerDetails = lecturerLine.split(";");
            if (lecturerDetails[1].equals(matricNo)) {
                student.close();
                lecturer.close();
                admin.close();
                type = 'L';
                return type;
            }
            lecturerLine = lecturer.readLine();
        }
        while (adminLine != null) {
            String[] adminDetails = adminLine.split(";");
            if (adminDetails[1].equals(matricNo)) {
                admin.close();
                student.close();
                lecturer.close();
                type = 'A';
                return type;
            }
            adminLine = admin.readLine();
        }

        student.close();
        lecturer.close();
        admin.close();
        return type;
    }

    // method to get all the password of the student
    public String getAllStudentsPassword() {
        String password = "";
        try {
            BufferedReader student = new BufferedReader(new FileReader("database/student.txt"));
            String studentLine = student.readLine();
            while (studentLine != null) {
                String[] studentDetails = studentLine.split(";");
                byte[] decodedKey = Base64.getDecoder().decode(studentDetails[5]);
                SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
                String decryptedPassword = decryptPassword(studentDetails[4], originalKey);
                password += String.format("| %-12s | %-40s | %-14s |\n", studentDetails[1], studentDetails[0],
                        decryptedPassword);
                studentLine = student.readLine();
            }
            student.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    // method to get all the password of the lecturer
    public String getAllLecturersPassword() {
        String password = "";
        try {
            BufferedReader admin = new BufferedReader(new FileReader("database/lecturer.txt"));
            String adminLine = admin.readLine();
            while (adminLine != null) {
                String[] adminDetails = adminLine.split(";");
                byte[] decodedKey = Base64.getDecoder().decode(adminDetails[4]);
                SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
                String decryptedPassword = decryptPassword(adminDetails[3], originalKey);
                password += String.format("| %-12s | %-40s | %-14s |\n", adminDetails[1], adminDetails[0],
                        decryptedPassword);
                adminLine = admin.readLine();
            }
            admin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    // method to access the file and return the data of the user based on the matric
    // number
    public String[] accessFile(String matricNo) throws Exception {
        char type = getTypeAccount(matricNo);
        String[] data = null;
        if (type == 'S') {
            BufferedReader student = new BufferedReader(new FileReader("database/student.txt"));
            String studentLine = student.readLine();
            while (studentLine != null) {
                String[] studentDetails = studentLine.split(";");
                if (studentDetails[1].equals(matricNo) && studentDetails[4].equals(getEncryptedPassword())) {
                    data = studentDetails;
                    student.close();
                    return data;
                }
                studentLine = student.readLine();
            }
            student.close();
        } else if (type == 'L') {
            BufferedReader lecturer = new BufferedReader(new FileReader("database/lecturer.txt"));
            String lecturerLine = lecturer.readLine();
            while (lecturerLine != null) {
                String[] lecturerDetails = lecturerLine.split(";");
                if (lecturerDetails[1].equals(matricNo) && lecturerDetails[3].equals(getEncryptedPassword())) {
                    data = lecturerDetails;
                    lecturer.close();
                    return data;
                }
                lecturerLine = lecturer.readLine();
            }
            lecturer.close();
        } else if (type == 'A') {
            BufferedReader admin = new BufferedReader(new FileReader("database/administrator.txt"));
            String adminLine = admin.readLine();
            while (adminLine != null) {
                String[] adminDetails = adminLine.split(";");
                if (adminDetails[1].equals(matricNo) && adminDetails[2].equals(getEncryptedPassword())) {
                    data = adminDetails;
                    admin.close();
                    return data;
                }
                adminLine = admin.readLine();
            }
            admin.close();
        }
        return data;
    }

    // method to create password
    public String createPassword() throws Exception {
        SecretKey encodedKey = getSecretKey();
        String secretKey = Base64.getEncoder().encodeToString(encodedKey.getEncoded());
        return getEncryptedPassword() + ";" + secretKey;
    }

    public boolean verifyPassword(String ID) throws Exception {
        char type = getTypeAccount(ID);
        if (type == 'S') {
            BufferedReader student = new BufferedReader(new FileReader("database/student.txt"));
            String studentLine = student.readLine();
            while (studentLine != null) {
                String[] studentDetails = studentLine.split(";");
                if (studentDetails[1].equals(ID) && studentDetails[4].equals(getEncryptedPassword())) {
                    student.close();
                    return true;
                }
                studentLine = student.readLine();
            }
            student.close();
        } else if (type == 'L') {
            BufferedReader lecturer = new BufferedReader(new FileReader("database/lecturer.txt"));
            String lecturerLine = lecturer.readLine();
            while (lecturerLine != null) {
                String[] lecturerDetails = lecturerLine.split(";");
                if (lecturerDetails[1].equals(ID) && lecturerDetails[3].equals(getEncryptedPassword())) {
                    lecturer.close();
                    return true;
                }
                lecturerLine = lecturer.readLine();
            }
            lecturer.close();
        } else if (type == 'A') {
            BufferedReader admin = new BufferedReader(new FileReader("database/administrator.txt"));
            String adminLine = admin.readLine();
            while (adminLine != null) {
                String[] adminDetails = adminLine.split(";");
                if (adminDetails[1].equals(ID) && adminDetails[2].equals(getEncryptedPassword())) {
                    admin.close();
                    return true;
                }
                adminLine = admin.readLine();
            }
            admin.close();
        }
        return false;
    }

    private String encryptPassword(String password, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(IV));
        byte[] encryptedPasswordBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedPasswordBytes);
    }

    public String decryptPassword(String encryptedPassword, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(IV));
        byte[] decryptedPasswordBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedPasswordBytes);
    }

}