import java.io.*;
// import java.util.*;
// import javax.crypto.SecretKey;

public class Lecturer extends User {

    // attributes
    private PasswordManager passwordManager = null;
    private String staffId;
    private String password;

    // normal constructor
    public Lecturer(String staffId, String password) throws Exception {
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
        PrintWriter pw = new PrintWriter(new FileWriter("database/lecturer.txt", true));
        try {
            pw.println(name + ";" + staffId + ";" + passwordManager.createPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.close();
    }

    public double calcAverageMarks() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("database/student.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        double totalMarks = 0;
        int studentCount = 0;
        // double averageMarks = 0;

        try {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                int marks = Integer.parseInt(data[4]);
                totalMarks += marks;
                studentCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        double averageMarks = totalMarks / studentCount;

        return averageMarks;
    }

    // public double calcTotalMarks() {
    // double totalMarks = 0;
    // for (int i = 0; i < students.length; i++) {
    // // totalMarks += students[i].getSubMarks();
    // }
    // return totalMarks;
    // }

    // toString method
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
