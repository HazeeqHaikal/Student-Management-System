public class Student {
    private String name;
    private String matricNo;
    private Subject[] subjects = new Subject[5];

    public Student(String name, String matricNo, Subject[] subjects) {
        this.name = name;
        this.matricNo = matricNo;
        for(int i = 0; i < subjects.length; i++){
            this.subjects[i] = subjects[i];
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricNo() {
        return this.matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public Subject[] getSubjects() {
        return this.subjects;
    }

    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }

    public String toString(){
        String joinSubject = "";
        for(int i = 0; i < subjects.length; i++){
            joinSubject += subjects[i].toString() + "\n";
        }
        return "Name: " + name + "\nMatric No: " + matricNo + "\nSubjects: " + joinSubject;
    }

}
