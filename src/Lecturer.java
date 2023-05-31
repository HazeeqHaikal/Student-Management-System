public class Lecturer {
    private String name;
    private String staffId;
    private Subject[] subjects = new Subject[5];
    private Student[] students = new Student[15];

    public Lecturer(String name, String staffId, Subject[] subjects, Student[] students) {
        this.name = name;
        this.staffId = staffId;
        for (int i = 0; i < 5; i++) {
            this.subjects[i] = subjects[i];
        }
        for (int i = 0; i < 15; i++) {
            this.students[i] = students[i];
        }
    }

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

    @Override
    public String toString() {
        return "Name: " + name + "\nStaff ID: " + staffId + "\nSubjects: " + subjects + "\nStudents: " + students;
    }

}
