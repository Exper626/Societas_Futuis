import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final Scanner input = new Scanner(System.in);;

        Main main = new Main();
        ClubFunc clubFunc = new ClubFunc();
        try {
            while(true){
                System.out.println("""
                        1 - Create club
                        2 - View clubs
                        3 - Update club details""");
                System.out.print("Option: ");
                String option = input.nextLine();

                //create and add club to the database
                if (option.equals("1")){
                    //get inputs from user
                    System.out.print("Club name: ");
                    String clubName = input.nextLine();

                    System.out.print("Club description");
                    String clubDescription = input.nextLine();

                    System.out.print("Membership criteria: ");
                    String clubMembershipCriteria = input.nextLine();

                    //generate clubID
                    String clubId = clubFunc.generateClubId();

                    //set club status as active
                    String clubStatus = "1";

                    //create new club
                    Club newClub = new Club(clubId, clubName, clubDescription, clubMembershipCriteria, clubStatus);

                    //add data to the database
                    clubFunc.addClub(newClub);


                }

                //view/filter clubs
                else if (option.equals("2")) {
                    System.out.println("\n1 - View all clubs\n" +
                            "2 - Search by id\n" +
                            "3 - Search by name\n" +
                            "0 - Go back to main menu\n");
                    System.out.print("Your option: ");
                    String subOption = input.nextLine();

                    // view all clubs
                    if (subOption.equals("1")){
                        System.out.println();
                        clubFunc.viewAllClubs();
                        break;
                    }

                    // search by id
                    else if (subOption.equals("2")) {
                        // get user input
                        System.out.print("Club ID: ");
                        String clubId = input.nextLine();

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
                    else if (subOption.equals("3")) {
                        // get user input
                        System.out.print("Club name: ");
                        String clubName = input.nextLine();

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
                    else if (subOption.equals("0")) {
                        break;
                    }
                }

                //update club
                else if (option.equals("3")) {
                    //view all clubs
                    clubFunc.viewAllClubs();

                    while(true){
                        //get user input(club id)
                        System.out.print("\nClub ID: ");
                        String clubId = input.nextLine();

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
                            String subOption = input.nextLine();

                            //update name
                            if (subOption.equals("1")){
                                //get updated club name and store it in updatedClubName variable
                                System.out.print("\nNew club name: ");
                                String updatedClubName = input.nextLine();

                                //update the data in the club table
                                clubFunc.updateClubName(clubId, updatedClubName);

                            }

                            //update club description
                            else if (subOption.equals("2")){
                                //get updated data
                                System.out.print("\nNew club description: ");
                                String updatedClubDescription = input.nextLine();

                                //update the data in the club table
                                clubFunc.updateClubDescription(clubId, updatedClubDescription);

                            }

                            //update membership criteria
                            else if (subOption.equals("3")) {
                                //get updated data
                                System.out.print("\nNew membership criteria: ");
                                String updatedClubMembershipCriteria = input.nextLine();

                                //update the data in the club table
                                clubFunc.updateClubMembershipCriteria(clubId, updatedClubMembershipCriteria);

                            }

                            //update club status
                            else if (subOption.equals("4")) {
                                boolean updatedClubStatus;
                                while(true){
                                    //get updated data
                                    System.out.print("\nNew membership criteria: ");
                                    String updatedClubStatusStr = input.nextLine();

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
                            else if (subOption.equals("0")) {
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
                else if (option.equals("4")) {
                    //view all clubs
                    clubFunc.viewAllClubs();

                    //get user input(club id)
                    System.out.print("\nClub ID: ");
                    String clubId = input.nextLine();

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
                        String subOption = input.nextLine();

                        // delete the selected club
                        if (subOption.equalsIgnoreCase("confirm")){
                            clubFunc.deleteClub(clubId);
                            break;
                        }

                        //go back to the menu
                        else if (subOption.equalsIgnoreCase("0")) {
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

                //invalid option
                else {
                    System.out.println("invalid option");
                }
            }
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