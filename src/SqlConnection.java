import java.sql.*;

public class SqlConnection {
    private Connection connection;

    //establish connection with database
    public void startConnection(){
        try {
            //load mysql jdbc driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            //connection url for jdbc
            String url = "jdbc:mysql://localhost:3306/test";

            //database credentials
            String username = "root";
            String password = "password";

            //establish connection with the database
            this.connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public ResultSet executeQuery(String query) throws SQLException{
        //create a PreparedStatement for executing the MySQL query
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //execute the query and return the result
        return preparedStatement.executeQuery();
    }

    public void insertData(String query){
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(query);

            //create a statement for executing the MySQL query
            Statement insertData = connection.createStatement();
            // Execute the query to insert, update, or delete data
            insertData.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    //update data of a table
    public void updateData(String query){
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(query);

            //create a statement for executing the MySQL query
            Statement updateData = connection.createStatement();
            // Execute the query to insert, update, or delete data
            updateData.executeUpdate(query);

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    // close connection with the database
    public void closeConnection(){
        try{
            //check if the connection is not null before attempting to close it
            if (connection != null) {
                // Close the database connection
                connection.close();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
