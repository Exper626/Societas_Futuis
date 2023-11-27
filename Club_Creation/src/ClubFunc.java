import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClubFunc {

    // add new clubs to database
    public void addClub(Club newClub) throws SQLException {
        //create sqlConnection object
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to insert data into database
        String query = "INSERT INTO test.club VALUES" + "('" + newClub.getClub_id() + "', '" + newClub.getClub_name() +
                "', '" + newClub.getClub_description() + "', true)";

        //Execute the query
        sqlConnection.insertData(query);

        System.out.println("added");

        //close connection
        sqlConnection.closeConnection();
    }

    // Display all existing clubs
    public void viewAllClubs() throws SQLException {
        //define variable
        int num =0;

        //create arrayList to hold the club details
        ArrayList<Club> clubListArray = getClubs();

        //Display the club details
        String line = "-".repeat(128);
        System.out.println(line);
        System.out.format("|%3s |%8s | %-30s | %-40s | %-20s | %10s |","No." , "Club ID", "Club Name", "Club Description", "Membership Criteria", "Status");
        System.out.println("");
        System.out.println(line);

        //loop through all the elements in the clubList array
        while(clubListArray.size() > num){
            Club tempClub = new Club(clubListArray.get(num).getClub_id(), clubListArray.get(num).getClub_name(), clubListArray.get(num).getClub_description(), clubListArray.get(num).getMembershipCriteria(), clubListArray.get(num).getClubStatus());

            System.out.format("|%3s |%8s | %-30s | %-40s | %-20s | %10s |", (num+1), tempClub.getClub_id(), tempClub.getClub_name(), tempClub.getClub_description(), tempClub.getMembershipCriteria(), tempClub.getClubStatus());
            System.out.println();
            num++;
        }
        System.out.println(line);
    }

    //get all the existing clubs and clubDetails
    public ArrayList<Club> getClubs() throws SQLException {
        //Create sqlConnection obj
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to get all club data from club table
        String query = "SELECT * FROM test.club;";

        //execute query and store the data in data ResultSet
        ResultSet data = sqlConnection.executeQuery(query);

        //create an arrayList
        ArrayList<Club> clubListArray = new ArrayList<>();

        //add clubs to the clubListArray as club obj(s)
        while (data.next()){
            //create a temp instance of club
            Club tempClub = new Club(data.getString("club_id"), data.getString("club_name"), data.getString("description"), data.getString("membership_criteria"), data.getString("status"));
            //add the club to the arraylist
            clubListArray.add(tempClub);
        }

        //return the arraylist
        return clubListArray;

    }
}

