import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private boolean isLoginAuth = false;
    private String userType;

    public User loginAdvisorAuth(String username, String password) throws SQLException {
        SqlConnection sqlConnection = new SqlConnection();
        sqlConnection.startConnection();
        String query = "select * from test.advisor";
        ResultSet data = sqlConnection.executeQuery(query);

        while(data.next()){
            String dataUsername = data.getString("advisor_id");
            String dataPassword = data.getString("password");
            String dataFirstName = data.getString("first_name");
            String dataLastName = data.getString("last_name");

            if ((username.equals(dataUsername)) && (password.equals(dataPassword))){
                isLoginAuth = true;
                String dataContactNumber = data.getString("contact_number");
                String dataEmail = data.getString("email");
                Advisor loggedAdvisor = new Advisor(dataUsername, dataPassword, dataFirstName, dataLastName, dataContactNumber, dataEmail);
                System.out.println("Welcome " + loggedAdvisor.getFirst_name() + "!");
                setUserType("a");
                return loggedAdvisor;

//                if (username.charAt(0) == 'a'){
//                    String dataContactNumber = data.getString("contact_number");
//                    String dataEmail = data.getString("email");
//                    Advisor loggedAdvisor = new Advisor(dataUsername, dataPassword, dataFirstName, dataLastName, dataContactNumber, dataEmail);
//                    System.out.println("Welcome " + loggedAdvisor.getFirst_name() + "!");
//                    setUserType("a");
//                    return loggedAdvisor;
//                } else if (username.charAt(0) == 's') {
//                    Student loggedStudent = new Student();
//                    setUserType("s");
//                    return loggedStudent;
//                }
            }
        }
        sqlConnection.closeConnection();
        setUserType("invalid");
        User invalidUser= new User();
        return invalidUser;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}