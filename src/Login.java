import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public boolean loginAuth(String username, String password) throws SQLException {
        SqlConnection sqlConnection = new SqlConnection();
        sqlConnection.startConnection();
        String query = "select advisor_id, password, first_name from test.advisor";
        ResultSet data = sqlConnection.executeQuery(query);

        while(data.next()){
            String dataUsername = data.getString("advisor_id");
            String dataPassword = data.getString("password");

            if ((username.equals(dataUsername)) && (password.equals(dataPassword))){
                System.out.println("Welcome " + data.getString("first_name"));
                sqlConnection.closeConnection();
                return true;
            }
        }
        sqlConnection.closeConnection();
        return false;
    }
}
