public class Advisor extends User{
    private String advisor_id;
    private String email;

    Advisor(String advisor_id,String password,String first_name, String last_name, String contact_number, String email){
        super(password, first_name, last_name, contact_number);
        this.advisor_id = advisor_id;
        this.email = email;
    }

    public String getAdvisor_id() {
        return this.advisor_id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
