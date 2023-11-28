// import necessary JDBC operations

import java.sql.*;
import java.util.Date;

public class SqlConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/event";
            String username = "root";
            String password = "GojoDaddy69#M3";

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM event");

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Club getClubByName(String club_name) {
        Club club = null;
        String query = "SELECT * FROM club WHERE club_name = ?";

        return club;
    }
    public Venue getVenueByName(String venue_name) {
        Venue venue = null;
        String query = "SELECT * FROM venue WHERE venue_name = ?";

        return venue;
    }

    public boolean isEventTimeSlotAvailable(String club_id, Date date, String event_time, Venue venue) {
        String query = "SELECT * FROM event WHERE club_id = ? AND date = ? AND event_time = ? AND venue_id = ?";
        boolean isAvailable = true;

        return isAvailable;
    }

    public void insertEvent (Event event) {
        // event id is auto incremented
        String query = "INSERT INTO event (event_name, club_id,event_description, date, event_time, venue_id ) VALUES (?,?,?,?,?,?)";

        /*try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public void updateEvent (Event event) {
        String query = "UPDATE event SET event_name = ?";

    }

    public void deleteEvent (Event event) {
        /*
        * Type in the query thing in database to delete the entire set of shit
        * */
        String query = "DELETE event";
    }
}
