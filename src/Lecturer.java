import java.io.*;
// import java.util.*;
// import javax.crypto.SecretKey;

public class Lecturer extends User {

    // attributes
    private PasswordManager passwordManager = null;
    private String staffId;
    private String password;
    private String classTeach;

    // default constructor
    public Lecturer() throws Exception {
        super("", "");
        this.classTeach = "";
        this.passwordManager = new PasswordManager("");
    }

    // normal constructor
    public Lecturer(String staffId, String password, String classTeach) throws Exception {
        super(staffId, password);
        this.classTeach = classTeach;
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

    public String getClassTeach() {
        return classTeach;
    }

    public void setClassTeach(String classTeach) {
        this.classTeach = classTeach;
    }

    // method to get student's grade
    public String getStudentsGrade(String[] lecturerFile) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("database/student.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        String[] data = null;
        String studentGrade = "";
        try {

            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (classTeach.equals(data[2])) {
                    int marks = Integer.parseInt(data[3]);
                    char grade = calcGrade(marks);
                    studentGrade += String.format("| %-12s | %-40s | %-8s | %-5s |", data[1], data[0], data[3], grade) + "\n";
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentGrade;
    }

    // method to verify if the student exists
    public boolean findStudent(String studentID) {
        BufferedReader br = null;
        boolean found = false;
        try {
            br = new BufferedReader(new FileReader("database/student.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        String[] data = null;
        try {
            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (studentID.equals(data[1])) {
                    found = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }

    // method to add grade to student
    public void addGrade(String studentID, int marks) {
        BufferedReader br = null;
        String line = "";
        String[] data = null;
        String newLine = "";
        try {
            br = new BufferedReader(new FileReader("database/student.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (studentID.equals(data[1]))
                    data[3] = Integer.toString(marks);

                newLine += data[0] + ";" + data[1] + ";" + data[2] + ";" + data[3] + ";" + data[4] + ";" + data[5]
                        + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("database/student.txt"));
            pw.print(newLine);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // polymorphism
    @Override
    public void createAccount(String name) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("database/lecturer.txt", true));
        try {
            pw.println(name + ";" + super.getID() + ";" + classTeach + ";" + passwordManager.createPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.close();
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


    // toString method
    @Override
    public String toString() {
        return "Staff " + super.toString();
    }

}
