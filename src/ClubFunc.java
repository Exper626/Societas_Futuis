import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        int num =0;

        ArrayList<Club> clubListArray = getClubs();

        String line = "-".repeat(128);
        System.out.println(line);
        System.out.format("|%3s |%8s | %-30s | %-40s | %-20s | %10s |","No." , "Club ID", "Club Name", "Club Description", "Membership Criteria", "Status");
        System.out.println("");
        System.out.println(line);

        while(clubListArray.size() > num){
            Club tempClub = new Club(clubListArray.get(num).getClub_id(), clubListArray.get(num).getClub_name(), clubListArray.get(num).getClub_description(), clubListArray.get(num).getMembershipCriteria(), clubListArray.get(num).getClubStatus());

            System.out.format("|%3s |%8s | %-30s | %-40s | %-20s | %10s |", num, tempClub.getClub_id(), tempClub.getClub_name(), tempClub.getClub_description(), tempClub.getMembershipCriteria(), tempClub.getClubStatus());
            System.out.println();
            num++;
        }
        System.out.println(line);
    }

    public ArrayList<Club> getClubs() throws SQLException {
        SqlConnection sqlConnection = new SqlConnection();
        sqlConnection.startConnection();
        String query = "SELECT * FROM test.club;";
        ResultSet data = sqlConnection.executeQuery(query);

        ArrayList<Club> clubListArray = new ArrayList<>();

        while (data.next()){
            Club tempClub = new Club(data.getString("club_id"), data.getString("club_name"), data.getString("description"), data.getString("membership_criteria"), data.getString("status"));
            clubListArray.add(tempClub);
        }

        System.out.println(clubListArray.get(1).getClub_id());
        return clubListArray;

    }
}

