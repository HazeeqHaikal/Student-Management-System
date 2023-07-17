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

    // open to either admin, student or lecturer txt file based on the roles
    public String[][] openFile(String roles) {
        // open file
        BufferedReader br = null;
        String[][] info = null;
        try {
            br = new BufferedReader(new FileReader("database/" + roles + ".txt"));
            int fileLine = Files.lines(Paths.get("database/" + roles + ".txt")).toArray().length;
            if (roles.equals("administrator"))
                info = new String[fileLine][4];
            else
                info = new String[fileLine][6];
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
                    info[i][j] = splitLine[j];
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

        return info;
    }

    // format all text file into a table format
    public void formatTextFile() throws Exception {
        String[][] adminInfo = openFile("administrator");
        String[][] studentInfo = openFile("student");
        String[][] lecturerInfo = openFile("lecturer");

        // format admin file
        String adminFormat = "===========================================================\n";
        adminFormat += String.format("| %-12s | %-40s |", "Admin ID", "Name");
        adminFormat += "\n===========================================================";
        for (int i = 0; i < adminInfo.length; i++) {
            adminFormat += String.format("\n| %-12s | %-40s |", adminInfo[i][1], adminInfo[i][0]);
        }
        adminFormat += "\n===========================================================";

        // format student file
        String studentFormat = "=========================================================================================\n";
        studentFormat += String.format("| %-12s | %-40s | %-11s | %-5s | %-5s |", "Student ID", "Name",
                "Class", "Marks", "Grade");
        studentFormat += "\n=========================================================================================";
        for (int i = 0; i < studentInfo.length; i++) {
            Lecturer lecturer = new Lecturer(studentInfo[i][0], "", studentInfo[i][2]);
            char grade = lecturer.calcGrade(Integer.parseInt(studentInfo[i][3]));
            studentFormat += String.format("\n| %-12s | %-40s | %-11s | %-5s | %-5s |", studentInfo[i][1],
                    studentInfo[i][0], studentInfo[i][2], studentInfo[i][3], grade);
        }
        studentFormat += "\n=========================================================================================";

        // format lecturer file
        String lecturerFormat = "=========================================================================\n";
        lecturerFormat += String.format("| %-12s | %-40s | %-11s |", "Staff ID", "Name", "Class Teach");
        lecturerFormat += "\n=========================================================================";
        for (int i = 0; i < lecturerInfo.length; i++) {
            lecturerFormat += String.format("\n| %-12s | %-40s | %-11s |", lecturerInfo[i][1], lecturerInfo[i][0],
                    lecturerInfo[i][2]);
        }
        lecturerFormat += "\n=========================================================================";

        // write to file
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("database/formatOutput.txt"));
            bw.write(adminFormat + "\n\n" + lecturerFormat + "\n\n" + studentFormat);
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String openFormat() {
        BufferedReader br = null;
        String format = "";
        try {
            br = new BufferedReader(new FileReader("database/formatOutput.txt"));
            String line = "";
            while ((line = br.readLine()) != null) {
                format += line + "\n";
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return format;
    }

    public void removeAccount(String id) {
        // open file
        String[][] adminInfo = openFile("administrator");
        String[][] studentInfo = openFile("student");
        String[][] lecturerInfo = openFile("lecturer");
        String accountType = "";

        // remove account
        for (int i = 0; i < adminInfo.length; i++) {
            if (adminInfo[i][1].equals(id)) {
                adminInfo[i][0] = "";
                adminInfo[i][1] = "";
                adminInfo[i][2] = "";
                adminInfo[i][3] = "";
                accountType = "administrator";
                break;
            }
        }

        for (int i = 0; i < studentInfo.length; i++) {
            if (studentInfo[i][1].equals(id)) {
                studentInfo[i][0] = "";
                studentInfo[i][1] = "";
                studentInfo[i][2] = "";
                studentInfo[i][3] = "";
                studentInfo[i][4] = "";
                studentInfo[i][5] = "";
                accountType = "student";
                break;
            }
        }

        for (int i = 0; i < lecturerInfo.length; i++) {
            if (lecturerInfo[i][1].equals(id)) {
                lecturerInfo[i][0] = "";
                lecturerInfo[i][1] = "";
                lecturerInfo[i][2] = "";
                lecturerInfo[i][3] = "";
                lecturerInfo[i][4] = "";
                accountType = "lecturer";
                break;
            }
        }

        // write to file
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("database/" + accountType + ".txt"));
            if (accountType.equals("administrator")) {
                for (int i = 0; i < adminInfo.length; i++) {
                    if (adminInfo[i][0].equals("") && adminInfo[i][1].equals("") && adminInfo[i][2].equals("")
                            && adminInfo[i][3].equals(""))
                        continue;
                    bw.write(adminInfo[i][0] + ";" + adminInfo[i][1] + ";" + adminInfo[i][2] + ";" + adminInfo[i][3]);
                    bw.newLine();
                }
            } else if (accountType.equals("lecturer")) {
                for (int i = 0; i < lecturerInfo.length; i++) {
                    if (lecturerInfo[i][0].equals("") && lecturerInfo[i][1].equals("") && lecturerInfo[i][2].equals("")
                            && lecturerInfo[i][3].equals("") && lecturerInfo[i][4].equals(""))
                        continue;
                    bw.write(lecturerInfo[i][0] + ";" + lecturerInfo[i][1] + ";" + lecturerInfo[i][2] + ";"
                            + lecturerInfo[i][3] + ";" + lecturerInfo[i][4]);
                    bw.newLine();
                }
            } else if (accountType.equals("student")) {
                for (int i = 0; i < studentInfo.length; i++) {
                    if (studentInfo[i][0].equals("") && studentInfo[i][1].equals("") && studentInfo[i][2].equals("")
                            && studentInfo[i][3].equals("") && studentInfo[i][4].equals("")
                            && studentInfo[i][5].equals(""))
                        continue;
                    bw.write(studentInfo[i][0] + ";" + studentInfo[i][1] + ";" + studentInfo[i][2] + ";"
                            + studentInfo[i][3] + ";" + studentInfo[i][4] + ";" + studentInfo[i][5]);
                    bw.newLine();
                }
            }
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