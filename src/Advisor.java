public class Advisor extends User{

    /* Contribution - Azhar, Hanaa 20221313/2237922 */
    /* Contribution - Haniffa, Haadiya 20211462/2237937 */
    /* Contribution - Napevithanage, Gouri 20210808/223794 */
    /* Contribution - Weerasinghe, Damitha 20210669/2236765 */

    private String advisor_id;
    private String email;

    private UserType type = UserType.ADVISOR;

    Advisor(){

    }

    Advisor(String advisor_id,String password,String first_name, String last_name, String contact_number, String email){
        super(password, first_name, last_name, contact_number);
        this.advisor_id = advisor_id;
        this.email = email;
    }


    //getter setter methods
    public String getAdvisor_id() {
        return this.advisor_id;
    }

    public String getEmail() {
        return this.email;
    }

    public UserType getType() {
        return this.type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdvisor_id(String advisor_id) {
        this.advisor_id = advisor_id;
    }
}
