import java.io.*;
// import java.util.*;
// import javax.crypto.SecretKey;

public class Lecturer extends User {

    // attributes
    private PasswordManager passwordManager = null;
    private String staffId;
    private String password;
    private String classTeach;

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
                    studentGrade += String.format("%-12s %-40s %-8s %-8s", data[1], data[0], data[3], grade) + "\n";
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentGrade;
    }

    public String findStudent(String studentName) {
        BufferedReader br = null;
        BufferedReader lecturer = null;

        try {
            br = new BufferedReader(new FileReader("database/student.txt"));
            lecturer = new BufferedReader(new FileReader("database/lecturer.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        String classTeach = "";
        String[] data = null;
        String studentGrade = "";
        try {
            while ((line = lecturer.readLine()) != null) {
                data = line.split(";");
                if (super.getID().equals(data[1])) {
                    classTeach = data[2];
                    break;
                }
            }

            int count = 0;
            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (classTeach.equals(data[2]) && studentName.equals(data[0])) {
                    count++;
                    studentGrade += String.format("%-4s %-40s %-10s", count, data[0], data[3]) + "\n";
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentGrade;
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
