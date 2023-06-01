public class Lecturer extends Subject {
    // attributes
    private String name;
    private String staffId;
    private Subject[] subjects = new Subject[5];
    private Student[] students = new Student[15];

    // normal constructor
    public Lecturer(String name, String staffId, Subject[] subjects, Student[] students, String subName,
            String subCode, int subMarks) {
                
        super(subName, subCode, subMarks);
        this.name = name;
        this.staffId = staffId;
        for (int i = 0; i < subjects.length; i++) {
            this.subjects[i] = subjects[i];
        }
        for (int i = 0; i < students.length; i++) {
            this.students[i] = students[i];
        }
    }

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

    public Subject[] getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    // where output is generated
    public String toString() {
        String joinSubject = "";
        String joinStudent = "";

        for (int i = 0; i < subjects.length; i++) {
            joinSubject += subjects[i].toString() + "\n";
        }

        for (int i = 0; i < students.length; i++) {
            joinStudent += students[i].toString() + "\n";
        }

        return "Name: " + name + "\nStaff ID: " + staffId + "\nSubjects: " + joinSubject + "\nStudents: " + joinStudent;
    }

}
