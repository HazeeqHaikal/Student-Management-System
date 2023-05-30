import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Concrete class implementing Student
// class RegularStudent extends Student {
//     private double marks;

//     public RegularStudent(String name, int rollNumber, double marks) {
//         super(name, rollNumber);
//         this.marks = marks;
//     }

//     public double getMarks() {
//         return marks;
//     }

//     @Override
//     public void displayDetails() {
//         System.out.println("Regular Student");
//         System.out.println("Name: " + getName());
//         System.out.println("Roll Number: " + getRollNumber());
//         System.out.println("Marks: " + getMarks());
//     }
}

// Concrete class implementing Student
// class DistanceLearningStudent extends Student {
//     private boolean isExempted;

//     public DistanceLearningStudent(String name, int rollNumber, boolean isExempted) {
//         super(name, rollNumber);
//         this.isExempted = isExempted;
//     }

//     public boolean isExempted() {
//         return isExempted;
//     }

//     @Override
//     public void displayDetails() {
//         System.out.println("Distance Learning Student");
//         System.out.println("Name: " + getName());
//         System.out.println("Roll Number: " + getRollNumber());
//         System.out.println("Exempted from Exams: " + isExempted());
//     }
// }

// Main class to demonstrate the student management system
public class StudentManagementSystem {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        // RegularStudent regularStudent = new RegularStudent("John Doe", 101, 85.5);
        // DistanceLearningStudent distanceLearningStudent = new DistanceLearningStudent("Jane Smith", 102, true);

        // students.add(regularStudent);
        // students.add(distanceLearningStudent);

        // Display student details
        // for (Student student : students) {
        //     student.displayDetails();
        //     System.out.println();
        // }

        // Modify student details
        // regularStudent.setName("John Smith");
        // distanceLearningStudent.setRollNumber(103);

        // Display modified student details
        // for (Student student : students) {
            // student.displayDetails();
        //     System.out.println();
        // }
    }
}