public class Subject {
    private String subName;
    private String subCode;
    private int subMarks;

    public Subject(String subName, String subCode, int subMarks) {
        this.subName = subName;
        this.subCode = subCode;
        this.subMarks = subMarks;
    }


    public String getSubName() {
        return this.subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubCode() {
        return this.subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public int getSubMarks() {
        return this.subMarks;
    }

    public void setSubMarks(int subMarks) {
        this.subMarks = subMarks;
    }

    public char calculateGrade() {
        char grade = '\u0000';

        if (subMarks <= 100 && subMarks >= 80) {
            grade = 'A';
        } else if (subMarks < 80 && subMarks >= 60) {
            grade = 'B';
        } else if (subMarks < 60 && subMarks >= 40) {
            grade = 'C';
        } else if (subMarks < 40 && subMarks >= 20) {
            grade = 'D';
        } else {
            grade = 'E';
        }

        return grade;
    }

    public String toString(){
        return "Subject Name: " + subName + "\nSubject Code: " + subCode + "\nSubject Marks: " + subMarks;
    }
}
