import java.sql.ResultSet;
import java.sql.SQLException;

public class Club {
    private String club_id;
    private String club_name;
    private String club_description;
    private boolean club_status = true;

    private SqlConnection sqlConnection = new SqlConnection();

    public Club(String club_name, String club_description){
        try{
            this.club_id = generateClubId();
        }
        catch (Exception e){
            System.out.println(e);
        }
        this.club_name = club_name;
        this.club_description = club_description;
    }

    public String generateClubId() throws SQLException {
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

    public String getClub_id() {
        return this.club_id;
    }

    public String getClub_description() {
        return this.club_description;
    }

    public String getClub_name() {
        return this.club_name;
    }

    public boolean getClubStatus(){
        return this.club_status;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public void setClub_description(String club_description) {
        this.club_description = club_description;
    }

    public void setClub_status(boolean club_status) {
        this.club_status = club_status;
    }
}
