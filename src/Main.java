import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner intInput = new Scanner(System.in);
        Scanner strInput = new Scanner(System.in);
        // Student student[] = new Student[15];
        // Subject subject[] = new Subject[5];

        System.out.print("Student (S) or Lecturer (L): ");
        char type = strInput.nextLine().charAt(0);

        System.out.println();

        if (type == 'S') {
            System.out.print("Name: ");
            String name = strInput.nextLine();

            System.out.print("Student ID: ");
            String studentID = strInput.nextLine();

            System.out.print("Marks: ");
            double marks = intInput.nextDouble();

            // student[0] = new Student(name, studentID, marks);
        } else if (type == 'L') {
            System.out.print("Staff Name: ");
            String name = strInput.nextLine();
            // capitalize every first letter of the word
            for (int i = 0; i < name.length(); i++) {
                if (i == 0) {
                    name = Character.toUpperCase(name.charAt(i)) + name.substring(1);
                }
                if (name.charAt(i) == ' ') {
                    name = name.substring(0, i + 1) + Character.toUpperCase(name.charAt(i + 1)) + name.substring(i + 2);
                }
            }

            System.out.print("Staff ID: ");
            String staffID = strInput.nextLine();

            System.out.print("\nStudent amount (0-99): ");
            int studentAmount = intInput.nextInt();

            Student student[] = new Student[studentAmount];
            // ArrayList<Student> student = new ArrayList<Student>();
            System.out.println();
            for (int i = 0; i < studentAmount; i++) {
                System.out.print("Student " + (i + 1) + " name: ");
                String studentName = strInput.nextLine();

                // capitalize every first letter of the word
                for (int j = 0; j < studentName.length(); j++) {
                    if (j == 0) {
                        studentName = Character.toUpperCase(studentName.charAt(j)) + studentName.substring(1);
                    }
                    if (studentName.charAt(j) == ' ') {
                        studentName = studentName.substring(0, j + 1) + Character.toUpperCase(studentName.charAt(j + 1))
                                + studentName.substring(j + 2);
                    }
                }

                System.out.print(studentName + "'s ID: ");
                String studentID = strInput.nextLine();

                System.out.print(studentName + "'s marks: ");
                double marks = intInput.nextDouble();

                // student.add(new Student(studentName, studentID, marks));
                student[i] = new Student(studentName, studentID, marks);

                System.out.println();
            }

            Lecturer lecturer = new Lecturer(name, staffID, studentAmount, student);
            System.out.print("Do you want to see your student's grade? (Y/N): ");
            char choice = strInput.nextLine().charAt(0);

            if (choice == 'Y') {
                System.out.println(lecturer.toString());
            } else if (choice == 'N') {
                System.out.println("Thank you for using this program.");
            } else {
                System.out.println("Invalid input.");
            }

        }

    }
}