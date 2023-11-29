import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /* Contribution - Azhar, Hanaa 20221313/2237922 */
    /* Contribution - Haniffa, Haadiya 20211462/2237937 */
    /* Contribution - Napevithanage, Gouri 20210808/223794 */
    /* Contribution - Weerasinghe, Damitha 20210669/2236765 */


    private final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        //create login obj
        Login login = new Login();
        //create main obj
        Main main = new Main();
        //create menuFunc obj
        MenuFunc menuFunc = new MenuFunc();
        //create eventFunc obj
        EventFunc eventFunc = new EventFunc();

        ClubFunc clubFunc = new ClubFunc();

        //define variables
        int option;
        String username, password, firstName, lastName, contactNumber, dateOfBirth, yearOfStudy;
        UserType userType;

        //loop main menu
        while(true){
            try{
                // print main main menu
                main.printNewMenu();
                //get user input
                System.out.print("Your option: ");
                //assign to user input to option
                option = main.input.nextInt();

                //register student
                if(option == 1){
                    //display instructions
                    main.printRegMenu();

                    while (true){
                        //get inputs from user
                        System.out.print("\nFirst name: ");
                        firstName = main.input.next();

                        //check whether the first name includes more than 50 characters
                        try {
                            if (firstName.length() > 50){
                                throw new Exception("\nMax characters for first/last name allowed is 50\n");
                            }else {
                                break;
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }


                    while (true){
                        System.out.print("Last name: ");
                        lastName = main.input.next();

                        //check whether the last name includes more than 50 characters
                        try {
                            if (lastName.length() > 50){
                                throw new Exception("\nMax characters for first/last name allowed is 50\n");
                            }else {
                                break;
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    while (true){
                        System.out.print("Contact number: ");
                        contactNumber = main.input.next();

                        //check whether the contact includes more than 13 characters
                        try {
                            if (contactNumber.length() > 13){
                                throw new Exception("\nMax characters for contact number allowed is 13\n");
                            }else {
                                break;
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    while(true){
                        System.out.print("Date of birth(Format:yyyy-mm-dd ): ");
                        dateOfBirth = main.input.next();
                        //validate date
                        try {
                            if (menuFunc.isValidDate(dateOfBirth)){
                                break;
                            }
                            throw new Exception("\nInvalid date\n");
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    while (true){
                        System.out.print("Grade of study: ");
                        yearOfStudy = main.input.next();
                        //validate grade
                        try {
                            if (menuFunc.isValidGrade(yearOfStudy)){
                                break;
                            }
                            throw new Exception("\nInvalid grade.\n");
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    while(true){
                        System.out.println("""
                                Password conditions,
                                - Password must be with in minimum length of 5 characters and maximum length of 20 characters.
                                - Password must include at least one numeric digit (0-9).
                                - Password must include at least one special character (e.g., !, @, #, $, %).
                                - Password cannot include spaces""");
                        System.out.print("Password: ");
                        password = main.input.next();
                        //validate the date
                        try {
                            if (menuFunc.isValidPassword(password)){
                                break;
                            }
                            throw new Exception("\nInvalid password.\n");
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    // create register obj
                    Register register = new Register();

                    //insert the userdetails to the database if user confirms
                    register.register(password, firstName, lastName, contactNumber, dateOfBirth, yearOfStudy);
                }

                // login (student/advisor)
                else if (option == 2) {

                    //display instructions
                    main.printLoginMenu();
                    //create a scanner obj
                    Scanner usernameInput = new Scanner(System.in);
                    //get user input(username)
                    System.out.print("\nUsername: ");
                    username = usernameInput.nextLine();

                    //create a scanner obj
                    Scanner passwordInput = new Scanner(System.in);
                    //get user input(password)
                    System.out.print("Password: ");
                    password = passwordInput.nextLine();

                    while(true){
                        try{
                            //get user type(advisor or student
                            userType = menuFunc.getUserType(username);

                            //advisor login
                            if (userType == UserType.ADVISOR){
                                //authenticate creds
                                Advisor loggedAdvisor = (Advisor) login.loginAdvisorAuth(username, password);
                                main.input.nextLine();


                                while (true){
                                    try{
                                        //print menu
                                        System.out.println();
                                        main.printLoggedAdvisorMenu();
                                        String subOption;
                                        
                                        //get user option
                                        System.out.print("Your option: ");
                                        subOption = main.input.nextLine();
                                        
                                        // view all clubs
                                        if (subOption.equals("1")){
                                            System.out.println();
                                            System.out.println("1 - View all clubs\n" +
                                                    "2 - Search by id\n" +
                                                    "3 - Search by name\n" +
                                                    "0 - Go back to main menu\n");
                                            System.out.print("Your option: ");
                                            String subSOption = main.input.nextLine();

                                            // view all clubs
                                            if (subSOption.equals("1")){
                                                System.out.println();
                                                clubFunc.viewAllClubs();
                                                System.out.println("Press enter...");
                                                break;
                                            }

                                            // search by id
                                            else if (subSOption.equals("2")) {
                                                // get user input
                                                System.out.print("Club ID: ");
                                                String clubId = main.input.nextLine();

                                                // search and get results
                                                ArrayList<Club> resultClub = clubFunc.searchClubById(clubId);

                                                //if the club doesn't exist
                                                if (resultClub.isEmpty()){
                                                    System.out.println("\nInvalid ID.");
                                                    break;
                                                }

                                                //display results
                                                clubFunc.displayClubResults(resultClub);
                                                break;
                                            }

                                            //search by name
                                            else if (subSOption.equals("3")) {
                                                // get user input
                                                System.out.print("Club name: ");
                                                String clubName = main.input.nextLine();

                                                // search and get results
                                                ArrayList<Club> resultClub = clubFunc.searchClubName(clubName);

                                                //if the club doesn't exist
                                                if (resultClub.isEmpty()){
                                                    System.out.println("\nInvalid name.");
                                                    break;
                                                }

                                                //display results
                                                clubFunc.displayClubResults(resultClub);
                                                break;
                                            }

                                            //go back
                                            else if (subSOption.equals("0")) {
                                                break;
                                            }
                                        }
                                        
                                        //Update club information
                                        else if (subOption.equals("2")) {
                                            //Display all the clubs
                                            clubFunc.viewAllClubs();

                                            while(true){
                                                //get user input(club id)
                                                System.out.print("\nClub ID: ");
                                                String clubId = main.input.nextLine();

                                                //search for existing club by clubID
                                                ArrayList<Club> clubDataArray = clubFunc.searchClubById(clubId);

                                                if (clubId.equals(clubDataArray.get(0).getClub_id())){
                                                    //if the club table is empty(there aren't clubs added)
                                                    if (clubDataArray.isEmpty()){
                                                        System.out.println("\nThere arent any clubs in the database");
                                                        break;
                                                    }

                                                    //display chosen club details
                                                    Club clubData = clubDataArray.get(0);
                                                    System.out.println("Club name: " + clubData.getClub_name() +"\n" +
                                                            "Club description: " + clubData.getClub_description() + "\n" +
                                                            "Membership criteria: " + clubData.getMembershipCriteria() + "\n" +
                                                            "Status: " + clubData.getClubStatus());

                                                    //display instructions
                                                    System.out.println("""
                                                            To update,
                                                            1 - Club name
                                                            2 - Club description
                                                            3 - Membership criteria
                                                            4 - Status
                                                            0 - Exit
                                                            Type the according number to update the data.""");

                                                    //get user input
                                                    System.out.print("Your option: ");
                                                    String subOptionA = main.input.nextLine();

                                                    //update name
                                                    if (subOptionA.equals("1")){
                                                        //get updated club name and store it in updatedClubName variable
                                                        System.out.print("\nNew club name: ");
                                                        String updatedClubName = main.input.nextLine();

                                                        //update the data in the club table
                                                        clubFunc.updateClubName(clubId, updatedClubName);

                                                    }

                                                    //update club description
                                                    else if (subOptionA.equals("2")){
                                                        //get updated data
                                                        System.out.print("\nNew club description: ");
                                                        String updatedClubDescription = main.input.nextLine();

                                                        //update the data in the club table
                                                        clubFunc.updateClubDescription(clubId, updatedClubDescription);

                                                    }

                                                    //update membership criteria
                                                    else if (subOptionA.equals("3")) {
                                                        //get updated data
                                                        System.out.print("\nNew membership criteria: ");
                                                        String updatedClubMembershipCriteria = main.input.nextLine();

                                                        //update the data in the club table
                                                        clubFunc.updateClubMembershipCriteria(clubId, updatedClubMembershipCriteria);

                                                    }

                                                    //update club status
                                                    else if (subOptionA.equals("4")) {
                                                        boolean updatedClubStatus;
                                                        while(true){
                                                            //get updated data
                                                            System.out.print("\nNew membership criteria: ");
                                                            String updatedClubStatusStr = main.input.nextLine();

                                                            if (updatedClubStatusStr.equalsIgnoreCase("active")){
                                                                updatedClubStatus = true;
                                                                break;
                                                            }

                                                            else if (updatedClubStatusStr.equalsIgnoreCase("inactive")){
                                                                updatedClubStatus = false;
                                                                break;
                                                            }
                                                            else {
                                                                System.out.println("Invalid status. Please type 'active' or 'inactive'.\n");
                                                            }
                                                        }

                                                        //update the data in the club table
                                                        clubFunc.updateClubStatus(clubId, updatedClubStatus);

                                                    }

                                                    //exit
                                                    else if (subOptionA.equals("0")) {
                                                        break;
                                                    }

                                                    //invalid option
                                                    else {
                                                        System.out.println("Invalid option\n");
                                                    }

                                                }else {
                                                    System.out.println("invalid ID, Please try again");
                                                }
                                            }
                                        }

                                        //delete club
                                        else if (subOption.equals("3")) {

                                            //view all clubs
                                            clubFunc.viewAllClubs();

                                            //get user input(club id)
                                            System.out.print("\nClub ID: ");
                                            String clubId = main.input.nextLine();

                                            //search for existing club by clubID
                                            ArrayList<Club> clubDataArray = clubFunc.searchClubById(clubId);

                                            if (clubId.equals(clubDataArray.get(0).getClub_id())){
                                                //if the club table is empty(there aren't clubs added)
                                                if (clubDataArray.isEmpty()){
                                                    System.out.println("\nThere arent any clubs in the database");
                                                    break;
                                                }

                                                //display chosen club details
                                                Club clubData = clubDataArray.get(0);
                                                System.out.println("Club name: " + clubData.getClub_name() +"\n" +
                                                        "Club description: " + clubData.getClub_description() + "\n" +
                                                        "Membership criteria: " + clubData.getMembershipCriteria() + "\n" +
                                                        "Status: " + clubData.getClubStatus());

                                                //display instructions
                                                System.out.println("""
                                                To delete type 'confirm' to proceed with the deletion.
                                                To go back to '0' to back to the menu""");

                                                //get user input
                                                System.out.print("Your option: ");
                                                String subOptionA = main.input.nextLine();

                                                // delete the selected club
                                                if (subOptionA.equalsIgnoreCase("confirm")){
                                                    clubFunc.deleteClub(clubId);
                                                    break;
                                                }

                                                //go back to the menu
                                                else if (subOptionA.equalsIgnoreCase("0")) {
                                                    System.out.println("Proceeding to the menu...");
                                                    break;
                                                }

                                                //invalid option
                                                else {
                                                    System.out.println("Invalid option. Please try again...");
                                                }


                                            }else {
                                                System.out.println("invalid ID, Please try again");
                                            }
                                        }

                                        //create event
                                        else if (subOption.equals("4")){
                                            //call create event method
                                            eventFunc.createEvent();

                                        }
                                        
                                        //view events
                                        else if (subOption.equals("5")) {

                                            //call viewEvents method
                                            eventFunc.viewEvents();
                                        }
                                        
                                        //delete event
                                        else if (subOption.equals("6")) {

                                            Scanner input = new Scanner(System.in);

                                            //view available events
                                            System.out.println();
                                            //call viewEvents method
                                            eventFunc.viewEvents();

                                            System.out.println();
                                            System.out.print("Enter event ID:");
                                            String eventId = input.nextLine();

                                            //call viewEvents method
                                            eventFunc.deleteEvent(eventId);
                                        }

                                        //attendance tracking
                                        else if (subOption.equals("7")){
                                            try {
                                                // Connect to the MySQL database
                                                String url = "jdbc:mysql://localhost:3306/societasfutuis";
                                                String user = "root";
                                                String passwordA = "password";
                                                Connection connection = DriverManager.getConnection(url, user, passwordA);

                                                // Create scanner for user input
                                                Scanner scanner = new Scanner(System.in);

                                                // Create AttendanceTracker object
                                                AttendanceTracker attendanceTracker = new AttendanceTracker(connection, scanner);

                                                // Main menu
                                                while (true) {
                                                    attendanceTracker.showMenu();
                                                }
                                            } catch (SQLException e) {
                                                ExceptionHandler.handleSQLException(e);
                                            }
                                        }

                                        //create club
                                        else if (subOption.equals("8")) {
                                            //get inputs from user
                                            System.out.print("Club name: ");
                                            String clubName = main.input.nextLine();

                                            System.out.print("Club description");
                                            String clubDescription = main.input.nextLine();

                                            System.out.print("Membership criteria: ");
                                            String clubMembershipCriteria = main.input.nextLine();

                                            //generate clubID
                                            String clubId = clubFunc.generateClubId();

                                            //set club status as active
                                            String clubStatus = "1";

                                            //create new club
                                            Club newClub = new Club(clubId, clubName, clubDescription, clubMembershipCriteria, clubStatus);

                                            //add data to the database
                                            clubFunc.addClub(newClub);
                                        }

                                        //exit
                                        else if (subOption.equals("0")){
                                            break;
                                        }
                                        else {
                                            throw new Exception("Invalid Option");
                                        }
                                    }

                                    catch (Exception e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }

                            //student login
                            else if (userType == UserType.STUDENT) {
                                //authenticate creds
                                Student loggedStudent = (Student) login.loginStudentAuth(username, password);

                                while(true){
                                    try{
                                        //print menu
                                        main.printLoggedAdvisorMenu();
                                        String subOption;

                                        //get user option
                                        System.out.print("Your option: ");
                                        subOption = main.input.nextLine();

                                        //join a club
                                        if (subOption.equals("1")){
                                            //view clubs
                                            System.out.println();
                                            clubFunc.viewAllClubs();

                                            //get user input
                                            System.out.println("Enter Club ID: ");
                                            String clubId = main.input.nextLine();

                                            if (clubFunc.isJoined(loggedStudent.getStudent_id(), clubId)){
                                                clubFunc.joinClub(loggedStudent.getStudent_id(), clubId);
                                                System.out.println();;
                                            }else {
                                                break;
                                            }
                                        }

                                        //view clubs
                                        else if (subOption.equals("2")){

                                            System.out.println("\n1 - View all clubs\n" +
                                                    "2 - Search by id\n" +
                                                    "3 - Search by name\n" +
                                                    "0 - Go back to main menu\n");
                                            System.out.print("Your option: ");
                                            String subSOption = main.input.nextLine();

                                            // view all clubs
                                            if (subSOption.equals("1")){
                                                System.out.println();
                                                clubFunc.viewAllClubs();
                                                break;
                                            }

                                            // search by id
                                            else if (subSOption.equals("2")) {
                                                // get user input
                                                System.out.print("Club ID: ");
                                                String clubId = main.input.nextLine();

                                                // search and get results
                                                ArrayList<Club> resultClub = clubFunc.searchClubById(clubId);

                                                //if the club doesn't exist
                                                if (resultClub.isEmpty()){
                                                    System.out.println("\nInvalid ID.");
                                                    break;
                                                }

                                                //display results
                                                clubFunc.displayClubResults(resultClub);
                                                break;
                                            }

                                            //search by name
                                            else if (subSOption.equals("3")) {
                                                // get user input
                                                System.out.print("Club name: ");
                                                String clubName = main.input.nextLine();

                                                // search and get results
                                                ArrayList<Club> resultClub = clubFunc.searchClubName(clubName);

                                                //if the club doesn't exist
                                                if (resultClub.isEmpty()){
                                                    System.out.println("\nInvalid name.");
                                                    break;
                                                }

                                                //display results
                                                clubFunc.displayClubResults(resultClub);
                                                break;
                                            }

                                            //go back
                                            else if (subSOption.equals("0")) {
                                                break;
                                            }
                                        }

                                        //view events
                                        else if (subOption.equals(("3"))){
                                            eventFunc.viewEvents();
                                        }

                                        //exit
                                        else if (subOption.equals("0")) {
                                            break;
                                        }

                                        else {
                                            throw new Exception("\nInvalid option, Please try again later.\n");
                                        }


                                    }

                                    catch (Exception e){
                                        System.out.println(e.getMessage());
                                    }
                                }

                            }

                            //invalid user
                            else if (userType == UserType.INVALID) {
                                //System.out.println("Invalid username, Please try again");
                                throw new Exception("\nInvalid username, Please try again...\n");
                            }
                        }
                        catch (ClassCastException e){
                            System.out.println("\nInvalid credentials. Please try again..\n");
                            break;
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                }
                //exit the program
                else if (option == 0) {
                    System.out.println("exiting...");
                    break;

                }
                //invalid user input given
                else {
                    //System.out.println("Invalid option. Please try again...");
                    throw new Exception("\nInvalid option. Please try again...\n");
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    public void printNewMenu(){
        String title = "Societas Futuis";
        System.out.println("\033[1m========== " + title +" ==========\033[0m");
        System.out.println("------------- Main Menu -------------");
        System.out.println("""
                Please select an option
                1 - Register
                2 - Log In
                0 - Quit
                """);
    }

    public void printLoginMenu(){
        System.out.println("\n------------- Login -------------");
        System.out.print("Please enter your username and password to proceed,");
    }

    public void printRegMenu(){
        System.out.println("\n------------- Registration -------------");
        System.out.print("Please enter the following details,");
    }

    public void printLoggedStudentMenu(){
        System.out.println("------------- Main Menu -------------");
        System.out.println("""
                1 - Join club.
                2 - View clubs.
                0 - Exit.""");
    }

    public void printLoggedAdvisorMenu(){
        System.out.println("------------- Main Menu -------------");
        System.out.println("""
                1 - View clubs.
                2 - Update club information.
                3 - Delete club.
                4 - Create an event.
                5 - View events.
                6 - Delete an event.
                7 - Attendance.
                8 - Create Club.
                0 - Exit.""");
    }


}