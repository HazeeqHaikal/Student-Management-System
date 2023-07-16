import java.io.*;

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
        String[][] studentInfo = openStudentFile();
        String studentGrade = "";
        for (int i = 0; i < studentInfo.length; i++) {
            if (classTeach.equals(studentInfo[i][2])) {
                int marks = Integer.parseInt(studentInfo[i][3]);
                char grade = calcGrade(marks);
                studentGrade += String.format("| %-12s | %-40s | %-8s | %-5s |", studentInfo[i][1], studentInfo[i][0],
                        studentInfo[i][3], grade) + "\n";
            }
        }

        return studentGrade;
    }

    // method to verify if the student exists
    public boolean findStudent(String studentID) {
        String[][] studentInfo = openStudentFile();
        boolean found = false;
        for (int i = 0; i < studentInfo.length; i++) {
            if (studentID.equals(studentInfo[i][1])) {
                found = true;
                break;
            }
        }
        return found;
    }

    // method to add grade to student
    public void addGrade(String studentID, int marks) {
        String[][] studentInfo = openStudentFile();
        for (int i = 0; i < studentInfo.length; i++) {
            if (studentID.equals(studentInfo[i][1]))
                studentInfo[i][3] = Integer.toString(marks);
        }

        String newLine = "";
        for (int i = 0; i < studentInfo.length; i++) {
            newLine += studentInfo[i][0] + ";" + studentInfo[i][1] + ";" + studentInfo[i][2] + ";" + studentInfo[i][3]
                    + ";" + studentInfo[i][4] + ";" + studentInfo[i][5] + "\n";
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
