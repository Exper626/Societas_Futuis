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
                return loggedAdvisor;
            }
        }
        sqlConnection.closeConnection();
        User temp= new User();
        return temp;
    }

    public User loginStudentAuth(String username, String password) throws SQLException {
        SqlConnection sqlConnection = new SqlConnection();
        sqlConnection.startConnection();
        String query = "select * from test.student";
        ResultSet data = sqlConnection.executeQuery(query);

        while(data.next()){
            String dataUsername = data.getString("student_id");
            String dataPassword = data.getString("password");
            String dataFirstName = data.getString("first_name");
            String dataLastName = data.getString("last_name");
            String dataContactNumber = data.getString("contact_number");
            String dateOfBirth = data.getString("date_of_birth");
            String grade = data.getString("year_of_study");

            if ((username.equals(dataUsername)) && (password.equals(dataPassword))){
                isLoginAuth = true;
                Student loggedStudent = new Student(dataUsername, dataPassword, dataFirstName, dataLastName, dataContactNumber, dataContactNumber, grade);
                System.out.println("Welcome " + loggedStudent.getFirst_name() + "!");
                return loggedStudent;
            }
        }
        sqlConnection.closeConnection();
        User temp= new User();
        return temp;

    }

}
