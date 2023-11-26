import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Register {
    private SqlConnection sqlConnection = new SqlConnection();
    private String studentId, password, firstName, lastName, contactNumber, dateOfBirth, YearOfStudy, confirmation;

    public void register(String password, String firstName, String lastName, String contactNumber, String dateOfBirth, String yearOfStudy){
        try{
            Scanner input = new Scanner(System.in);
            sqlConnection.startConnection();

            studentId = generateStudentID();

            while(true){
                String confirmation;
                System.out.println("Please type 'c' to confirm the details, or 'q' to go back to the menu.\n" +
                        "Username: " + studentId +
                        "\nPassword: " + password +
                        "\nFirst name: " + firstName +
                        "\nLast name: " + lastName +
                        "\nContact number: " + contactNumber +
                        "\nDate of birth: " + dateOfBirth +
                        "\nyear of study: " + yearOfStudy);

                System.out.print("Type here: ");
                confirmation = input.nextLine();

                setConfirmation(confirmation);

                if (getConfirmation().equals("c")){

                    System.out.println("You have successfully registered!");
                }else {
                    break;
                }
            }
        }
        catch (Exception e ){
            System.out.println(e);
        }

    }

    public String getConfirmation() {
        return this.confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String generateStudentID() throws SQLException {
        sqlConnection.startConnection();
        String getLatestId = "SELECT * FROM test.student ORDER BY student_id DESC LIMIT 1";
        ResultSet latestIdResult = sqlConnection.executeQuery(getLatestId);
        String strNextId = "s000001";
        while(latestIdResult.next()){
            String strLatestId = latestIdResult.getNString("student_id");
            strLatestId = strLatestId.substring(1);
            int intLatestId = Integer.parseInt(strLatestId);
            int intNextId = intLatestId + 1;
            strNextId = String.format("s%06d", intNextId);
        }
        return strNextId;
    }
}
