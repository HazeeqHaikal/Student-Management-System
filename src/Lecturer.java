import java.util.ArrayList;

public class Lecturer {
    // attributes
    private String name;
    private String staffId;
    private int amountStudent;
    // private Subject[] subjects = new Subject[5];

    private Student[] students;
    // private ArrayList<Student> students = new ArrayList<Student>();

    // normal constructor

    // public Lecturer(String name, String staffId, int studentAmount,
    // ArrayList<Student> students) {
    public Lecturer(String name, String staffId, int studentAmount, Student[] students) {
        this.name = name;
        this.staffId = staffId;
        this.amountStudent = studentAmount;
        this.students = new Student[studentAmount];
        for (int i = 0; i < students.length; i++) {
            this.students[i] = students[i];
        }
    }

    // public Lecturer(String name, String staffId, Subject[] subjects, Student[]
    // students, String subName,
    // String subCode, int subMarks) {

    // super(subName, subCode, subMarks);
    // this.name = name;
    // this.staffId = staffId;
    // for (int i = 0; i < subjects.length; i++) {
    // this.subjects[i] = subjects[i];
    // }
    // for (int i = 0; i < students.length; i++) {
    // this.students[i] = students[i];
    // }
    // }

    // getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    // public Subject[] getSubjects() {
    // return subjects;
    // }

    // public void setSubjects(Subject[] subjects) {
    // this.subjects = subjects;
    // }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public double calcAverageMarks() {
        double totalMarks = 0;
        for (int i = 0; i < students.length; i++) {
            totalMarks += students[i].getSubMarks();
        }
        return totalMarks / students.length;
    }

    public double calcTotalMarks() {
        double totalMarks = 0;
        for (int i = 0; i < students.length; i++) {
            totalMarks += students[i].getSubMarks();
        }
        return totalMarks;
    }

    // where output is generated
    public String toString() {
        // String joinSubject = "";
        String joinStudent = "";

        // for (int i = 0; i < subjects.length; i++) {
        // joinSubject += subjects[i].toString() + "\n";
        // }

        for (int i = 0; i < students.length; i++) {
            joinStudent += students[i].toString() + "\n\n";
        }

        // return "Name: " + name + "\nStaff ID: " + staffId + "\nSubjects: " +
        // joinSubject + "\nStudents: " + joinStudent;
        return "Name: " + name + "\nStaff ID: " + staffId + "\n\nStudents: " + joinStudent + "\n" + "Average Marks: "
                + String.format("%.2f", calcAverageMarks()) + "%" + "\nTotal Marks: " + calcTotalMarks() + "%";
    }

}
