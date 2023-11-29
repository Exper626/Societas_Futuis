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
                "', '" + newClub.getClub_description() + "', '" + newClub.getMembershipCriteria() + "', true)";

        //Execute the query
        sqlConnection.insertData(query);

        System.out.println("added");

        //close connection
        sqlConnection.closeConnection();
    }

    // Display all existing clubs
    public void viewAllClubs() throws SQLException {
        //define variable
        int num = 0, clubNum =0;

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

            if (tempClub.getClub_description().length() > 40){
                String temp = tempClub.getClub_description();
                tempClub.setClub_description(temp.substring(0, 35) + "...");
            }

            System.out.format("|%3s |%8s | %-30s | %-40s | %-20s | %10s |", (clubNum+1), tempClub.getClub_id(), tempClub.getClub_name(), tempClub.getClub_description(), tempClub.getMembershipCriteria(), tempClub.getClubStatus());
            System.out.println();
            num++;
            clubNum++;
        }
        System.out.println(line);
    }

    //search clubs from name
    public ArrayList<Club> searchClubName(String name) throws SQLException {
        ArrayList<Club> clubData = getClubs(), clubResult = new ArrayList<Club>();

        int size = 0;

        //check and return if the club exists
        while (clubData.size() > size){
            if ((clubData.get(size).getClub_name()).toLowerCase().contains((name).toLowerCase())){
                clubResult.add(clubData.get(size));
            }
            size++;
        }

        return clubResult;

    }

    //search clubs from id
    public ArrayList<Club> searchClubById(String clubId) throws SQLException {
        //get all the existing clubs and clubDetails and store in an arrayList
        ArrayList<Club> clubData = getClubs(), clubResult = new ArrayList<Club>();

        int size = 0;

        //check and return if the club exists
        while (clubData.size() > size){
            if (clubData.get(size).getClub_id().equals(clubId)){
                clubResult.add(clubData.get(size));
                return clubResult;
            }
            size++;
        }

        return clubResult;
    }

    //display club info
    public void displayClubResults(ArrayList<Club> clubData){
        int num = 1, i = 0;


        System.out.println();
        String line = "-".repeat(128);
        System.out.println(line);
        System.out.format("|%3s |%8s | %-30s | %-40s | %-20s | %10s |","No." , "Club ID", "Club Name", "Club Description", "Membership Criteria", "Status");
        System.out.println("");
        System.out.println(line);

        //loop through the array
        while(clubData.size() > i){
            Club temp = clubData.get(i);
            i++;
            System.out.format("|%3s |%8s | %-30s | %-40s | %-20s | %10s |", (num), temp.getClub_id(), temp.getClub_name(), temp.getClub_description(), temp.getMembershipCriteria(), temp.getClubStatus());
            System.out.println();
            num++;
        }

        System.out.println(line);
    }

    //update club name in club table
    public void updateClubName(String clubId, String updatedClubName){
        //create sqlConnection object
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to insert data into database
        String query = "update test.club " +
                "set" +
                " club_name = '" + updatedClubName + "' where club_id = '" + clubId + "'" ;

        //Execute the query
        sqlConnection.insertData(query);

        System.out.println("Successfully updated!");

        //close connection to the databse
        sqlConnection.startConnection();
    }

    //update club description in club table
    public void updateClubDescription(String clubId, String updatedClubDescription){
        //create sqlConnection object
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to update data club table
        String query = "update test.club " +
                "set " +
                "description = '" + updatedClubDescription + "' where club_id = '" + clubId + "'" ;

        //Execute the query
        sqlConnection.insertData(query);

        System.out.println("Successfully updated!");

        sqlConnection.startConnection();
    }

    //update club membershipCriteria
    public void updateClubMembershipCriteria(String clubId, String updatedClubMembershipCriteria){
        //create sqlConnection object
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to update membership criteria in club table
        String query = "update test.club " +
                "set " +
                "membership_criteria = '" + updatedClubMembershipCriteria + "' where club_id = '" + clubId + "'" ;

        //Execute the query
        sqlConnection.insertData(query);

        System.out.println("Successfully updated!");

        //close connection to the databse
        sqlConnection.startConnection();
    }

    //update club membershipCriteria
    public void updateClubStatus(String clubId, boolean updatedClubStatus){
        //create sqlConnection object
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to update club status in club table
        String query = "update test.club " +
                "set " +
                "membership_criteria = '" + updatedClubStatus + "' where club_id = '" + clubId + "'" ;

        //Execute the query
        sqlConnection.insertData(query);

        System.out.println("Successfully updated!");

        //close connection to the database
        sqlConnection.startConnection();
    }

    //delete club
    public void deleteClub(String clubId){
        //create sqlConnection object
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to update club status in club table
        //update after putting all the functions together
        String deleteFromEventClub = "delete from test.event_club where club_id = '" + clubId + "';";
        String deleteFromAdvisorClub = "delete from test.club_advisor where club_id = '" + clubId + "';";
        String deleteFromStudentClub = "delete from test.student_club where club_id = '" + clubId + "';";
        String deleteFromClub = "delete from test.club where club_id = '" + clubId + "';";


        //Execute the query
        sqlConnection.updateData(deleteFromEventClub);
        sqlConnection.updateData(deleteFromAdvisorClub);
        sqlConnection.updateData(deleteFromStudentClub);
        sqlConnection.updateData(deleteFromClub);

        System.out.println("Successfully updated!");


        //close connection to the database
        sqlConnection.startConnection();
    }

    //get all the existing clubs and clubDetails
    private ArrayList<Club> getClubs() throws SQLException {
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

    public String generateClubId() throws SQLException {
        SqlConnection sqlConnection = new SqlConnection();
        sqlConnection.startConnection();
        String getLatestId = "SELECT * FROM test.club ORDER BY club_id DESC LIMIT 1";
        ResultSet latestIdResult = sqlConnection.executeQuery(getLatestId);
        String strNextId = "c000";
        while(latestIdResult.next()){
            String strLatestId = latestIdResult.getNString("club_id");
            strLatestId = strLatestId.substring(1);
            int intLatestId = Integer.parseInt(strLatestId);
            int intNextId = intLatestId + 1;
            strNextId = String.format("c%03d", intNextId);
        }
        sqlConnection.closeConnection();
        return strNextId;
    }
}

