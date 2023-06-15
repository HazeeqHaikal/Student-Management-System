import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner intInput = new Scanner(System.in);
        Scanner strInput = new Scanner(System.in);
        PasswordManager passwordManager = null;

        System.out.println("Welcome to the Student Management System!");

        System.out.print("\nDo you want to login (L) or register (R)?: ");
        char loginOrRegister = strInput.nextLine().charAt(0);
        loginOrRegister = Character.toUpperCase(loginOrRegister);

        // loop until user enters a valid choice
        while (loginOrRegister != 'L' && loginOrRegister != 'R') {
            System.out.println("\nInvalid choice!");
            System.out.print("Do you want to login (L) or register (R)?: ");
            loginOrRegister = strInput.nextLine().charAt(0);
            loginOrRegister = Character.toUpperCase(loginOrRegister);
        }

        // clearing terminal
        System.out.flush();

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
                System.out.println("\nAccount not found!");
                intInput.close();
                strInput.close();
                return;
            }

            // if password is wrong print out error message
            if (!passwordManager.verifyPassword(ID)) {
                System.out.println("\nWrong password!");
                intInput.close();
                strInput.close();
                return;
            }

            // welcome message with their name from the txt filpe
            System.out.println("\nWelcome " + data[0] + "!");

            // if account is a student
            if (typeAccount == 'S') {
                System.out.print("1. Check your password\n2. Check your grades\n\nEnter your choice: ");
                int choice = intInput.nextInt();

                System.out.flush();

                if (choice == 1) {
                    System.out.println("\nYour password is: " + passwordManager.getDecryptedPassword());
                } else if (choice == 2) {
                    // Student student = new Student(ID, password);
                    // student.calcTotalMarks();
                    // System.out.println("\nYour grades are: " + data[5]);
                } else {
                    System.out.println("\nInvalid choice!");
                }
            }

            // if account is a lecturer
            else if (typeAccount == 'L') {
                System.out.println("1. Check your password\n2. Check your students' grades\n\nEnter your choice: ");
                int choice = intInput.nextInt();

                System.out.flush();

                if (choice == 1) {
                    System.out.println("\nYour password is: " + passwordManager.getDecryptedPassword());
                } else if (choice == 2) {
                    // Lecturer lecturer = new Lecturer(ID, password);
                    // lecturer.calcTotalMarks();
                    // System.out.println("\nYour students' grades are: " + data[5]);
                } else {
                    System.out.println("\nInvalid choice!");
                }
            }

            // if account is an admin
            else if (typeAccount == 'A') {
                System.out.print(
                        "1. Check all student's password\n2. Check all lecturer's password\n3. Add admin account\n\nEnter your choice: ");

                int choice = intInput.nextInt();

                System.out.flush();

                if (choice == 1) {
                    System.out.println("\nAll student's password\n");
                    System.out.println(passwordManager.getAllStudentsPassword());
                } else if (choice == 2) {
                    System.out.println("\nAll lecturer's password\n");
                    System.out.println(passwordManager.getAllLecturersPassword());
                } else if (choice == 3) {
                    System.out.println("Enter the admin's name: ");
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
                        System.out.println("\nAccount already exists!");
                        intInput.close();
                        strInput.close();
                        return;
                    }

                    // create admin account
                    Administrator admin = new Administrator(adminID, adminPassword);
                    // admin.createAdminAcc(data[0]);
                    admin.createAccount(adminName);
                    System.out.println("\nAccount created successfully!");
                    System.out.println();
                    System.out.println("ID: " + adminID + "\nPassword: " + adminPassword);
                } else {
                    System.out.println("\nInvalid choice!");
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
                System.out.println("\nPasswords do not match!");
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
                System.out.println("\nAccount already exists!");
                intInput.close();
                strInput.close();
                return;
            }

            // if user input is not S, or L print out error message
            if (userType != 'S' && userType != 'L') {
                System.out.println("\nInvalid user type!");
                intInput.close();
                strInput.close();
                return;
            }

            // creating student or lecturer account
            if (userType == 'S') {
                Student student = new Student(ID, password);
                // student.createStudentAcc(name);
                student.createAccount(name);
            } else if (userType == 'L') {
                Lecturer lecturer = new Lecturer(ID, password);
                // lecturer.createLecturerAcc(name);
                lecturer.createAccount(name);
            }

            System.out.println("\nAccount created successfully!");
            System.out.println();
            System.out.println("ID: " + ID + "\nPassword: " + password);

        }

        // close the input
        System.out.print("Press enter to continue...");
        System.in.read();

        intInput.close();
        strInput.close();
    }

}
