import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        String club_name = "yasqueen";
        String club_desc = "mhmmmm yaaasss";

        Club newClub = new Club(club_name, club_desc);

        Main main = new Main();
        try{
            main.addClub(newClub);
        }
        catch (Exception e){
            System.out.println(e);
        }



    }

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
}