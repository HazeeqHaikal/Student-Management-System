import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner intInput = new Scanner(System.in);
        Scanner strInput = new Scanner(System.in);
        PasswordManager passwordManager = null;
        Student student = new Student("", "", "");
        Lecturer lecturer = new Lecturer("", "", "");
        Administrator admin = new Administrator("", "");

        System.out.println("Welcome to the Student Management System!");

        System.out.print("\nDo you want to login (L), register (R) or exit (E)?: ");
        char loginOrRegister = strInput.nextLine().charAt(0);
        loginOrRegister = Character.toUpperCase(loginOrRegister);

        // loop until user enters a valid choice
        while (loginOrRegister != 'L' && loginOrRegister != 'R' && loginOrRegister != 'E') {
            System.out.println("\nInvalid choice!");
            System.out.print("\nDo you want to login (L), register (R) or exit (E)?: ");
            loginOrRegister = strInput.nextLine().charAt(0);
            loginOrRegister = Character.toUpperCase(loginOrRegister);
        }

        // clearing terminal
        System.out.flush();

        // exit program
        if (loginOrRegister == 'E') {
            ((User) student).greenText("\nThank you for using the Student Management System!");
            intInput.close();
            strInput.close();
            return;
        }
        // login page
        if (loginOrRegister == 'L') {
            System.out.println("\nLogin Page");

            System.out.print("\nEnter your ID: ");
            String ID = strInput.nextLine();

            System.out.print("Enter your password: ");
            String password = strInput.nextLine();

            passwordManager = new PasswordManager(password);
            boolean isFound = passwordManager.findAccount(ID);
            char typeAccount = passwordManager.getTypeAccount(ID);
            String[] data = passwordManager.accessFile(ID);

            System.out.flush();
            // if account doesn't exist in any of the files print out error message
            if (!isFound) {
                ((User) student).redText("\nAccount not found!");
                // failedPrompt = "\n\nAccount not found!";
                intInput.close();
                strInput.close();
                return;
            }

            // if password is wrong print out error message
            if (!passwordManager.verifyPassword(ID)) {
                ((User) student).redText("\nWrong password!");
                intInput.close();
                strInput.close();
                return;
            }

            // welcome message with their name from the txt file
            System.out.println("\nWelcome " + data[0] + "!\n");

            // loop until user enters E to exit
            while (loginOrRegister != 'E') {
                // if account is a student
                if (typeAccount == 'S') {
                    System.out.print("1. Check your password\n2. Check your grades\n\nEnter your choice: ");
                    int choice = intInput.nextInt();

                    System.out.flush();

                    if (choice == 1) {
                        System.out.println("\nYour password is: " + passwordManager.getDecryptedPassword());
                    } else if (choice == 2) {
                        student = new Student(ID, password, data[2]);
                        System.out.println("\nYour marks are: " + data[3]);
                        System.out.println("Your grade is: " + student.calcGrade(Integer.parseInt(data[3])));
                    } else {
                        System.out.println("\nInvalid choice!");
                    }
                }

                // if account is a lecturer
                else if (typeAccount == 'L') {
                    lecturer = new Lecturer(ID, password, data[2]);
                    String studentInfo = lecturer.getStudentsGrade(data);

                    System.out.print(
                            "1. Check your password\n2. Check your students' grades\n3. Add student's grades\n\nEnter your choice: ");
                    int choice = intInput.nextInt();

                    System.out.flush();

                    if (choice == 1) {
                        System.out.println("\nYour password is: " + passwordManager.getDecryptedPassword());
                    } else if (choice == 2) {
                        if (studentInfo == null) {
                            System.out.println("\nNo students in your class!");
                            continue;
                        }
                        System.out.println("\nClass you are teaching: " + data[2] + "\n");
                        System.out.println(
                                "==============================================================================");
                        System.out.printf("| %-12s | %-40s | %-8s | %-5s |", "Matric No.", "Student Name", "Marks",
                                "Grade");
                        System.out.print(
                                "\n==============================================================================");

                        System.out.print("\n" + studentInfo);
                        // put = to close the table
                        System.out.print(
                                "==============================================================================\n");

                    } else if (choice == 3) {
                        System.out.println(
                                "==============================================================================");
                        System.out.printf("| %-12s | %-40s | %-8s | %-4s |", "Matric No.", "Student Name", "Marks",
                                "Grade");
                        System.out.print(
                                "\n==============================================================================");
                        System.out.print("\n" + studentInfo);
                        System.out.print(
                                "==============================================================================\n");

                        System.out.print("\nDo you want to edit multiple people? (Y/N): ");
                        char editMultiple = strInput.nextLine().charAt(0);
                        editMultiple = Character.toUpperCase(editMultiple);

                        if (editMultiple == 'N') {

                            System.out.print("\nEnter the student's ID: ");
                            String studentID = strInput.nextLine();

                            System.out.print("Enter the student's marks: ");
                            int studentMarks = intInput.nextInt();

                            boolean isFoundStudent = lecturer.findStudent(studentID);

                            // if student doesn't exist print out error message
                            if (!isFoundStudent) {
                                ((User) lecturer).redText("\nStudent not found!");
                                continue;
                            }

                            // add student's grade
                            lecturer.addGrade(studentID, studentMarks);
                            ((User) lecturer).greenText("\nGrade added successfully!");
                        } else if (editMultiple == 'Y') {
                            System.out.print("\nEnter matric number of students you want to edit split by commas: ");
                            String[] studentIDs = strInput.nextLine().split(",");
                            // trim the spaces
                            for (int i = 0; i < studentIDs.length; i++) {
                                studentIDs[i] = studentIDs[i].trim();
                            }
                            Student[] studentArray = new Student[studentIDs.length];

                            for (int i = 0; i < studentIDs.length; i++) {
                                studentArray[i] = new Student(studentIDs[i], "", data[2]);

                                boolean isFoundStudent = lecturer.findStudent(studentIDs[i]);

                                // if student doesn't exist print out error message
                                if (!isFoundStudent) {
                                    ((User) lecturer).redText("\nStudent not found!");
                                    continue;
                                }

                                System.out.print("Enter the " + studentIDs[i] + "'s marks: ");
                                int studentMarks = intInput.nextInt();

                                // add student's grade
                                lecturer.addGrade(studentIDs[i], studentMarks);
                                ((User) lecturer).greenText("\nGrade added successfully!");

                                System.out.println();
                            }
                        }

                    } else {
                        System.out.println("\nInvalid choice!");
                    }

                }

                // if account is an admin
                else if (typeAccount == 'A') {
                    System.out.print(
                            "1. Check all student's password\n2. Check all lecturer's password\n3. Add admin account\n4. Remove account\n5.. Format text file\n\nEnter your choice: ");

                    int choice = intInput.nextInt();

                    System.out.flush();

                    if (choice == 1) {
                        System.out.println("\nAll student's password\n");
                        System.out.println(passwordManager.getAllStudentsPassword());
                    } else if (choice == 2) {
                        System.out.println("\nAll lecturer's password\n");
                        System.out.println(passwordManager.getAllLecturersPassword());
                    } else if (choice == 3) {
                        System.out.print("Enter the admin's name: ");
                        String adminName = strInput.nextLine();

                        System.out.print("\nEnter the admin's ID: ");
                        String adminID = strInput.nextLine();

                        System.out.print("Enter the admin's password: ");
                        String adminPassword = strInput.nextLine();

                        System.out.print("Confirm the admin's password: ");
                        String adminConfirmPassword = strInput.nextLine();

                        // loop until password and confirm password variable value is the same
                        while (!adminPassword.equals(adminConfirmPassword)) {
                            System.out.println("\nPasswords do not match!");
                            System.out.print("Enter the admin's password: ");
                            adminPassword = strInput.nextLine();

                            System.out.print("Confirm the admin's password: ");
                            adminConfirmPassword = strInput.nextLine();
                        }

                        passwordManager = new PasswordManager(adminPassword);
                        boolean isFoundAdmin = passwordManager.findAccount(adminID);

                        System.out.flush();

                        // if account already exists print out error message
                        if (isFoundAdmin) {
                            ((User) student).redText("\nAccount already exists!");
                            continue;
                        }

                        // create admin account
                        admin = new Administrator(adminID, adminPassword);
                        admin.createAccount(adminName);
                        ((User) admin).greenText("\nAccount created successfully!");
                        System.out.println();
                        System.out.println("ID: " + adminID + "\nPassword: " + adminPassword);
                    } else if (choice == 4) {
                        // ((User) admin).openFormat();
                        System.out.println(((User) admin).openFormat());
                        System.out.print("Enter the ID of the account you want to remove: ");
                        String removeID = strInput.nextLine();

                        isFound = passwordManager.findAccount(removeID);

                        if (removeID.equals(ID)) {
                            ((User) admin).redText("\nYou cannot remove your own account!\n");
                            continue;
                        }

                        // if account doesn't exist print out error message
                        if (!isFound) {
                            ((User) admin).redText("\nAccount not found!\n");
                            continue;
                        }

                        // remove admin account
                        ((User) admin).removeAccount(removeID);
                        ((User) admin).greenText("\nAccount removed successfully!");
                        ((User) admin).formatTextFile();
                    } else if (choice == 5) {
                        System.out.println("\nFormatting text file...");
                        ((User) admin).formatTextFile();
                        ((User) admin).greenText("\nText file formatted successfully!");
                    } else {
                        System.out.println("\nInvalid choice!");
                    }
                }

                System.out.print("Do you want to exit? (Y/N): ");
                char exit = strInput.nextLine().charAt(0);
                exit = Character.toUpperCase(exit);

                // loop until user enters a valid choice
                while (exit != 'Y' && exit != 'N') {
                    System.out.println("\nInvalid choice!");
                    System.out.print("\nDo you want to exit? (Y/N): ");
                    exit = strInput.nextLine().charAt(0);
                    exit = Character.toUpperCase(exit);
                }

                // clearing terminal
                System.out.flush();

                // exit program
                if (exit == 'Y') {
                    ((User) lecturer).greenText("\nThank you for using the Student Management System!");
                    intInput.close();
                    strInput.close();
                    loginOrRegister = 'E';
                    break;
                }
            }
        }

        // registration page
        else if (loginOrRegister == 'R') {
            System.out.println("\nRegistration Page");

            System.out.print("\nAre you a student (S) or a lecturer (L)?: ");
            char userType = strInput.nextLine().charAt(0);
            userType = Character.toUpperCase(userType);

            System.out.print("\nEnter your name: ");
            String name = strInput.nextLine();
            name = name.toUpperCase();

            System.out.print("Enter your ID: ");
            String ID = strInput.nextLine();

            System.out.print("Enter your password: ");
            String password = strInput.nextLine();

            System.out.print("Confirm your password: ");
            String confirmPassword = strInput.nextLine();

            System.out.flush();

            // loop until password and confirm password variable value is the same
            while (!password.equals(confirmPassword)) {
                System.out.printf("\u001B[31m" + "\n\nPasswords do not match!" + "\u001B[0m");
                System.out.print("Enter your password: ");
                password = strInput.nextLine();

                System.out.print("Confirm your password: ");
                confirmPassword = strInput.nextLine();

                System.out.flush();
            }

            passwordManager = new PasswordManager(password);
            boolean isFound = passwordManager.findAccount(ID);

            // if account already exists print out error message
            if (isFound) {
                student = new Student("", "", "");
                ((User) student).redText("\nAccount already existed!");
                intInput.close();
                strInput.close();
                return;
            }

            // if user input is not S, or L print out error message
            if (userType != 'S' && userType != 'L') {
                student = new Student("", "", "");
                ((User) student).redText("\nInvalid user type!");
                intInput.close();
                strInput.close();
                return;
            }

            // creating student or lecturer account
            if (userType == 'S') {
                System.out
                        .print("\n1. RCDCS1102A\n2. RCDCS1102B\n3. RCDCS1102C\n\nEnter the class that you are in (1-3): ");
                int classChoiceInt = intInput.nextInt();
                String classChoice = "";
                if (classChoiceInt == 1) {
                    classChoice = "RCDCS1102A";
                } else if (classChoiceInt == 2) {
                    classChoice = "RCDCS1102B";
                } else if (classChoiceInt == 3) {
                    classChoice = "RCDCS1102C";
                } else {
                    ((User) student).redText("\nInvalid choice!");
                    intInput.close();
                    strInput.close();
                    return;
                }
                student = new Student(ID, password, classChoice);
                student.createAccount(name);
            } else if (userType == 'L') {
                System.out
                        .print("\n1. RCDCS1102A\n2. RCDCS1102B\n3. RCDCS1102C\n\nEnter the class that you teach (1-3): ");
                int classChoiceInt = intInput.nextInt();
                String classChoice = "";
                if (classChoiceInt == 1) {
                    classChoice = "RCDCS1102A";
                } else if (classChoiceInt == 2) {
                    classChoice = "RCDCS1102B";
                } else if (classChoiceInt == 3) {
                    classChoice = "RCDCS1102C";
                } else {
                    ((User) lecturer).redText("\nInvalid choice!");
                    intInput.close();
                    strInput.close();
                    return;
                }

                lecturer = new Lecturer(ID, password, classChoice);
                lecturer.createAccount(name);
            }

            // casting student or lecturer object to user object
            User user = (User) (userType == 'S' ? student : lecturer);
            user.greenText("\nAccount created successfully!");
            System.out.println("\nID: " + ID + "\nPassword: " + password);
            user.greenText("\nPress enter to continue...");
            System.in.read();

        }

        // close the input
        intInput.close();
        strInput.close();
    }

}
