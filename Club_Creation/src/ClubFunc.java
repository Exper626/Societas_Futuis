import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubFunc {
    public void addClub(Club newClub) throws SQLException {
        SqlConnection sqlConnection = new SqlConnection();
        sqlConnection.startConnection();
        String query = "INSERT INTO test.club VALUES" + "('" + newClub.getClub_id() + "', '" + newClub.getClub_name() +
                "', '" + newClub.getClub_description() + "', true)";
        System.out.println(query);
        sqlConnection.insertData(query);
        System.out.println("added");
        sqlConnection.closeConnection();
    }

    public void viewAllClubs() throws SQLException {
        SqlConnection sqlConnection = new SqlConnection();
        sqlConnection.startConnection();
        String query = "SELECT * FROM test.club;";
        ResultSet data = sqlConnection.executeQuery(query);
        String line = "-".repeat(123);
        System.out.println(line);
        System.out.format("|%8s | %-30s | %-40s | %-20s | %10s |", "Club ID", "Club Name", "Club Description", "Membership Criteria", "Status");
        System.out.println("");
        System.out.println(line);

        while(data.next()){

            Club tempClub = new Club(data.getString("club_id"), data.getString("club_name"), data.getString("description"), data.getString("membership_criteria"), data.getString("status"));

            System.out.format("|%8s | %-30s | %-40s | %-20s | %10s |", tempClub.getClub_id(), tempClub.getClub_name(), tempClub.getClub_description(), tempClub.getMembershipCriteria(), tempClub.getClubStatus());
            System.out.println();
        }
        System.out.println(line);
    }
}

