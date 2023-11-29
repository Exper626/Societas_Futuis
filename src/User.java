public class User {

    /* Contribution - Weerasinghe, Damitha 20210669/2236765 */

    private String password;
    private String first_name = "lama";
    private String last_name;
    private String contact_number;

    public User() {

    }

    User(String password, String first_name, String last_name, String contact_number){
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.contact_number = contact_number;
    }

    public String getContact_number() {
        return this.contact_number;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
