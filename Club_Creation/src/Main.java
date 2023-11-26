import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        ClubFunc clubFunc = new ClubFunc();
        try {
            clubFunc.viewAllClubs();
        }

        catch (Exception e){
            System.out.println(e);
        }


    }

    public void printNewMenu(){
        String title = "Societas Futuis";
        System.out.println("\033[1m========== " + title +" ==========\033[0m");
        System.out.println("\n");
        System.out.println("---------- Menu ----------");
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