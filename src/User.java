import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class User {
    // attributes
    private String ID;
    private String password;

    // default constructor
    public User() {
        this.ID = "";
        this.password = "";
    }

    // normal constructor
    public User(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    // getter and setter methods
    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // abstract method
    public abstract void createAccount(String name) throws IOException;

    // open student txt file and return in 2d array
    public String[][] openStudentFile() {
        // open file
        BufferedReader br = null;
        String[][] studentInfo = null;
        try {
            br = new BufferedReader(new FileReader("database/student.txt"));
            int fileLine = Files.lines(Paths.get("database/student.txt")).toArray().length;
            studentInfo = new String[fileLine][6];
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // read file
        String line = "";
        int i = 0;
        try {
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(";");
                for (int j = 0; j < splitLine.length; j++) {
                    studentInfo[i][j] = splitLine[j];
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // close file
        try {
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return studentInfo;
    }

    // write to student txt file
    public void writeStudentFile(String[][] studentInfo) {
        // open file
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("database/student.txt"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // write to file
        try {
            for (int i = 0; i < studentInfo.length; i++) {
                for (int j = 0; j < studentInfo[i].length; j++) {
                    bw.write(studentInfo[i][j]);
                    if (j != studentInfo[i].length - 1) {
                        bw.write(";");
                    }
                }
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // close file
        try {
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // open lecturer txt file and return in 2d array
    public String[][] openLecturerFile() {
        // open file
        BufferedReader br = null;
        String[][] lecturerInfo = null;
        try {
            br = new BufferedReader(new FileReader("database/lecturer.txt"));
            int fileLine = Files.lines(Paths.get("database/lecturer.txt")).toArray().length;
            lecturerInfo = new String[fileLine][6];
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // read file
        String line = "";
        int i = 0;
        try {
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(";");
                for (int j = 0; j < splitLine.length; j++) {
                    lecturerInfo[i][j] = splitLine[j];
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // close file
        try {
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lecturerInfo;
    }

    // read admin txt file and return in 2d array
    public String[][] openAdminFile() {
        // open file
        BufferedReader br = null;
        String[][] adminInfo = null;
        try {
            br = new BufferedReader(new FileReader("database/administrator.txt"));
            int fileLine = Files.lines(Paths.get("database/administrator.txt")).toArray().length;
            adminInfo = new String[fileLine][4];
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // read file
        String line = "";
        int i = 0;
        try {
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(";");
                for (int j = 0; j < splitLine.length; j++) {
                    adminInfo[i][j] = splitLine[j];
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // close file
        try {
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return adminInfo;
    }

    // format all text file into a table format
    public void formatTextFile() throws Exception {
        String[][] adminInfo = openAdminFile();
        String[][] studentInfo = openStudentFile();
        String[][] lecturerInfo = openLecturerFile();

        // format admin file
        String adminFormat = String.format("| %-12s | %-40s |", "Admin ID", "Name");
        for (int i = 0; i < adminInfo.length; i++) {
            adminFormat += String.format("\n| %-12s | %-40s |", adminInfo[i][1], adminInfo[i][0]);
        }

        // format student file
        String studentFormat = String.format("| %-12s | %-40s | %-10s | %-5s | %-5s |", "Student ID", "Name",
                "Class", "Marks", "Grade");
        for (int i = 0; i < studentInfo.length; i++) {
            Lecturer lecturer = new Lecturer(studentInfo[i][0], "", studentInfo[i][2]);
            char grade = lecturer.calcGrade(Integer.parseInt(studentInfo[i][3]));
            studentFormat += String.format("\n| %-12s | %-40s | %-8s | %-5s | %-5s |", studentInfo[i][1],
                    studentInfo[i][0], studentInfo[i][2], studentInfo[i][3],  grade);
        }

        // format lecturer file
        String lecturerFormat = String.format("| %-12s | %-40s | %-10s |", "Staff ID", "Name", "Class");
        for (int i = 0; i < lecturerInfo.length; i++) {
            lecturerFormat += String.format("\n| %-12s | %-40s | %-10s |", lecturerInfo[i][1], lecturerInfo[i][0],
                    lecturerInfo[i][2]);
        }

        // write to file
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("database/administratorFormat.txt"));
            bw.write(adminFormat);
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            bw = new BufferedWriter(new FileWriter("database/studentFormat.txt"));
            bw.write(studentFormat);
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            bw = new BufferedWriter(new FileWriter("database/lecturerFormat.txt"));
            bw.write(lecturerFormat);
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // makes the text green in the console for successful operations
    public void greenText(String text) {
        String[] splitText = text.split("");
        // no matter how long the text is, it will always take 1.5 seconds to print
        long sleepTime = (long) (1.5 / splitText.length * 1000);
        for (int i = 0; i < splitText.length; i++) {
            System.out.printf("\u001B[32m" + splitText[i] + "\u001B[0m");
            try {
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    // makes the text red in the console for failed operations
    public void redText(String text) {
        String[] splitText = text.split("");
        // no matter how long the text is, it will always take 1.5 seconds to print
        long sleepTime = (long) (1.5 / splitText.length * 1000);
        for (int i = 0; i < splitText.length; i++) {
            System.out.printf("\u001B[31m" + splitText[i] + "\u001B[0m");
            try {
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    // tostring
    public String toString() {
        return "ID: " + ID + "\nPassword: " + password;
    }

}