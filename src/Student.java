import java.util.Date;

public class Student extends User{
    private String student_id;
    private String DOT;
    private String year_of_study;

    public Student(){

    }

    Student(String student_id,String password, String first_name, String last_name, String contact_number, String dot, String year_of_study) {
        super(password, first_name, last_name, contact_number);
        this.student_id = student_id;
        this.DOT = dot;
        this.year_of_study = year_of_study;
    }

    public String getStudent_id() {
        return this.student_id;
    }

    public String getDOT() {
        return this.DOT;
    }

    public String getYear_of_study() {
        return this.year_of_study;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void setYear_of_study(String year_of_study) {
        this.year_of_study = year_of_study;
    }

    public void setDOT(String DOT) {
        this.DOT = DOT;
    }
}
