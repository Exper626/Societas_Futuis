import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class SqlConnection {
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String username = "root";
    private static String password = "password";
    private Connection connection;

    public void startConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public ResultSet executeQuery(String query) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    public void insertData(String query){
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(query);
            Statement insertData = connection.createStatement();
            insertData.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public void closeConnection(){
        try{
            if (connection != null) {
                connection.close();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
