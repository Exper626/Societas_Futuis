import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Register {
    private SqlConnection sqlConnection = new SqlConnection();
    private String studentId, password, firstName, lastName, contactNumber, dateOfBirth, YearOfStudy, confirmation;
    private Student newStudent;

    public void register(String password, String firstName, String lastName, String contactNumber, String dateOfBirth, String yearOfStudy){
        try{
            //Creating scanner obj
            Scanner input = new Scanner(System.in);

            //start connection between the database and the code
            sqlConnection.startConnection();

            //generate unique identifier for student
            this.studentId = generateStudentID();

            //Display student details to confirm
            System.out.println("\nPlease type 'c' to confirm the details, or 'q' to go back to the menu.\n" +
                    "Username: " + this.studentId +
                    "\nPassword: " + password +
                    "\nFirst name: " + firstName +
                    "\nLast name: " + lastName +
                    "\nContact number: " + contactNumber +
                    "\nDate of birth: " + dateOfBirth +
                    "\nyear of study: " + yearOfStudy);

            while(true){
                String confirmation;

                //get user input
                System.out.print("Type here: ");
                confirmation = input.nextLine();

                //set confirmation variable to user's input
                setConfirmation(confirmation);

                //if the user confirms the details to complete the registration process
                if (confirmation.equals("c")){
                    this.confirmation = "confirmed";
                    String query = "insert into test.student values" +
                            "('" + studentId +"', '" + password + "', '" + firstName + "', '" + lastName + "', '" + contactNumber + "', '" + dateOfBirth + "', '" + yearOfStudy +  "');";
                    sqlConnection.insertData(query);

                    System.out.println(firstName + ", Welcome!");
                    System.out.println("You have successfully registered!\n");
                    break;
                }
                //go back to the menu
                else if (confirmation.equals("m")){
                    this.confirmation ="goBack";
                    break;
                }
                //when user gives an invalid input
                else {
                    System.out.println("\nInvalid option. Please try again...");
                }
            }
        }
        catch (Exception e ){
            System.out.println(e);
        }

    }

    // get the value of the confirmation variable
    public String getConfirmation() {
        return this.confirmation;
    }

    // setter for confirmation variable
    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    // generate unique identifier for student
    public String generateStudentID() throws SQLException {
        // establish connection with database
        sqlConnection.startConnection();

        // MySQL query to get the last row of the student table
        String getLatestId = "SELECT * FROM test.student ORDER BY student_id DESC LIMIT 1";
        //execute the query
        ResultSet latestIdResult = sqlConnection.executeQuery(getLatestId);

        //default unique identifier if students doesn't exist in the student table
            String strNextId = "s000001";

        //loop through the data in the student table
        while(latestIdResult.next()){
            //get the student_id from the resultSet
            String strLatestId = latestIdResult.getNString("student_id");
            //remove the first letter if the id
            strLatestId = strLatestId.substring(1);
            // convert the string to integer
            int intLatestId = Integer.parseInt(strLatestId);
            //add +1
            int intNextId = intLatestId + 1;
            //convert the id to string in the format of s000000
            strNextId = String.format("s%06d", intNextId);
        }
        //return the generated id
        return strNextId;
    }

    //getter method for new student
    public Student getNewStudent() {
        return newStudent;
    }
}
