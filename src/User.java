import java.io.*;

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

    // opens the txt file and returns the contents as a string array for student
    public String[][] openStudentFile() {
        // create a buffered reader object
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("database/student.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        String[][] fileContents = null;
        try {
            fileContents = new String[br.readLine().length()][6];
            // read the file line by line
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                for (int j = 0; j < 6; j++) {
                    fileContents[i][j] = data[j];
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // close the file
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContents;
    }

    // opens the txt file and returns the contents as a string array for lecturer
    public String[][] openAdminFile() {
        // create a buffered reader object
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("database/lecturer.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        String[][] fileContents = null;
        try {
            fileContents = new String[br.readLine().length()][4];
            // read the file line by line
            int i = 0;
            while ((line = br.readLine()) != null) {
                // split the line by the comma
                String[] data = line.split(";");
                // add the line to the arraylist
                for (int j = 0; j < data.length; j++) {
                    fileContents[i][j] = data[j];
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // close the file
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContents;
    }

    // opens the txt file and returns the contents as a string array for admin
    public String[][] openLecturerFile() {
        // create a buffered reader object
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("database/admin.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        String[][] fileContents = null;
        try {
            fileContents = new String[br.readLine().length()][5];
            // read the file line by line
            int i = 0;
            while ((line = br.readLine()) != null) {
                // split the line by the comma
                String[] data = line.split(";");
                // add the line to the arraylist
                for (int j = 0; j < data.length; j++) {
                    fileContents[i][j] = data[j];
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // close the file
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContents;
    }

    // writes to the txt file for student
    public void writeStudentFile(String[][] data) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("database/student.txt"));
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    pw.print(data[i][j]);
                    if (j != data[i].length - 1) {
                        pw.print(";");
                    }
                }
                pw.println();
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
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