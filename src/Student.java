public class Student extends Subject {
    // attributes
    private String name;
    private String matricNo;
    // private Subject[] subjects = new Subject[5];

    // normal constructor
    // public Student(String name, String matricNo, Subject[] subjects, String
    // subName, String subCode, int subMarks) {
    // super(subName, subCode, subMarks);
    // this.name = name;
    // this.matricNo = matricNo;
    // for (int i = 0; i < subjects.length; i++) {
    // this.subjects[i] = subjects[i];
    // }
    // }

    public Student(String name, String matricNo, double subMarks) {
        super(subMarks);
        this.name = name;
        this.matricNo = matricNo;
    }

    // getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    // public Subject[] getSubjects() {
    // return subjects;
    // }

    // public void setSubjects(Subject[] subjects) {
    // this.subjects = subjects;
    // }

    // where output is generated
    public String toString() {
        String formattedMarks = String.format("%.2f", getSubMarks());
        return String.format("%-20s\n%-20s\n%-20s\n%-20s", "Name: " + name, "Matric No: " + matricNo,
                "Marks: " + formattedMarks + " %", "Grade: " + calculateGrade());
        // return String.format("%s\n%s\n%f\n%c\n", "Name: " + name, "Matric No: " +
        // matricNo,
        // "Marks: " + getSubMarks() + "%",
        // "Grade: " + calculateGrade());
    }

    // public String toString() {
    // String joinSubject = "";
    // for (int i = 0; i < subjects.length; i++) {
    // joinSubject += subjects[i].toString() + "\n";
    // }
    // return "Name: " + name + "\nMatric No: " + matricNo + "\nSubjects: " +
    // joinSubject;
    // }

}
