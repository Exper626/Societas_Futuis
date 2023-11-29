import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class EventFunc {

    /* Contribution - Azhar, Hanaa 20221313/2237922 */

    //create an event
    public void createEvent() throws SQLException {
        // scanner class
        Scanner scanner = new Scanner(System.in);

        //SqlConnection Class
        SqlConnection sqlConnection = new SqlConnection();
        //ClubFunc class
        ClubFunc clubFunc = new ClubFunc();
        //EventFun Class
        EventFunc eventFunc = new EventFunc();


        //search for the club
        while (true) {
            try {
                // Step 1: Check if the club exists in the database
                System.out.print("\nEnter the club ID: ");
                String club_name = scanner.nextLine();

                //search for the club
                ArrayList<Club> clubArray = clubFunc.searchClubById(club_name);

                if (clubArray.isEmpty()) {
                    throw new Exception("\nInvalid club ID\n");
                } else {
                    System.out.println("\nClub: " + clubArray.get(0).getClub_name());

                    // Step 2: Check the availability
                    ArrayList<Venue> venueData = eventFunc.getVenueList();
                    // Display venueList
                    eventFunc.viewVenueList();


                    //get venue
                    System.out.print("\nEnter venue number: ");
                    int venueNumber = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        Venue selectedVenue = venueData.get(venueNumber);

                    } catch (Exception e) {
                        System.out.println("Invalid venue number");
                    }


                    //get date
                    String dateString;
                    while (true) {
                        System.out.println();
                        System.out.print("Enter the date (yyyy-mm-dd): ");
                        dateString = scanner.nextLine();

                        String pattern = "yyyy-MM-dd";

                        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

                        try {
                            Date date = dateFormat.parse(dateString);
                            break;
                        } catch (ParseException e) {
                            System.out.println(e.getMessage());;
                        }
                    }

                    String venueId = venueData.get(venueNumber).getVenue_id();

                    if (isAvailable(dateString, venueId)) {
                        //generate event id
                        String eventId = generateEventId();

                        //get event name
                        System.out.print("\nEvent name: ");
                        String eventName = scanner.nextLine();

                        //get event description
                        System.out.print("\nEvent description: ");
                        String eventDescription = scanner.nextLine();

                        //get time
                        System.out.print("\nEvent time (HH:MM-HH:MM): ");
                        String time = scanner.nextLine();

                        addEvent(eventId, eventName, eventDescription, dateString, time, venueId);

                        System.out.println("Event created!");

                    }


                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

    //view events
    public void viewEvents() throws SQLException {
        //define variable
        int num = 0, venueNum = 0;

        //create arrayList
        ArrayList<Event> eventData = getEventList();

        //display venue details
        String line = "-".repeat(97);
        System.out.println(line);
        System.out.format("|%3s |%10s | %-30s | %-20s | %-20s |", "No.", "Event ID", "Event Name", "Date", "Time");
        System.out.println("");
        System.out.println(line);

        while (eventData.size() > num) {
            Event tempVenue = new Event(eventData.get(num).getEvent_id(), eventData.get(num).getEvent_name(), eventData.get(num).getEvent_description(), eventData.get(num).getDate(), eventData.get(num).getEvent_time());

            System.out.format("|%3s |%-10s | %-30s | %-20s | %-20s |", venueNum, tempVenue.getEvent_id(), tempVenue.getEvent_name(), tempVenue.getDate(), tempVenue.getEvent_time());
            System.out.println();
            num++;
            venueNum++;
        }
        System.out.println(line);

    }

    //join event
    public void joinEvent(String studentID, String eventID){
        //create sqlConnection object
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to insert data into database
        String query = "insert into student_event values ('" + studentID + "', '" + eventID + "', null";

        //Execute the query
        sqlConnection.insertData(query);

        System.out.println("Joined!");

        //close connection
        sqlConnection.closeConnection();
    }

    public boolean isJoined(String studentID, String eventID) throws SQLException {
        //create sqlConnection object
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to insert data into database
        String query = "select * from student_event where student_id = '" + studentID + "' and event_id = '" + eventID + "';";

        //execute query and store the data in data ResultSet
        ResultSet data = sqlConnection.executeQuery(query);

        return !data.next();
    }

    //update event
    public void updateEvent(Event eventUpdated, String eventId){
        //create sqlConnection object
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to insert data into database
        String query = "update event " +
                "set event_name = '" + eventUpdated.getEvent_name() + "', event_description = '" + eventUpdated.getEvent_description() + "', " +
                "date = '" + eventUpdated.getDate() + "', time_slot = '" + eventUpdated.getEvent_time() + "' where event_id = '" + eventId + "';";

        //Execute the query
        sqlConnection.insertData(query);

        System.out.println("Successfully updated!");

        //close connection to the databse
        sqlConnection.startConnection();
    }

    //delete event
    public void deleteEvent(String eventId){
        //create sqlConnection object
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to update club status in club table
        String deleteFromEventClub = "delete from event_club where event_id = '" + eventId + "';";
        String deleteFromEventVenue = "delete from event_venue where event_id = '" + eventId + "';";
        String deleteFromEvent = "delete from event where event_id = '" + eventId + "';";

        //Execute the query
        sqlConnection.updateData(deleteFromEventClub);
        sqlConnection.updateData(deleteFromEventVenue);
        sqlConnection.updateData(deleteFromEvent);

        System.out.println("Successfully updated!");


        //close connection to the database
        sqlConnection.startConnection();
    }

    public void viewVenueList() throws SQLException {
        //define variable
        int num = 0, venueNum = 0;

        //create arrayList
        ArrayList<Venue> venueData = getVenueList();

        //display venue details
        String line = "-".repeat(51);
        System.out.println(line);
        System.out.format("|%3s |%10s | %-30s |", "No.", "Club ID", "Club Name");
        System.out.println("");
        System.out.println(line);

        while (venueData.size() > num) {
            Venue tempVenue = new Venue(venueData.get(num).getVenue_id(), venueData.get(num).getVenue_name());

            System.out.format("|%3s |%10s | %-30s |", venueNum, tempVenue.getVenue_id(), tempVenue.getVenue_name());
            System.out.println();
            num++;
            venueNum++;
        }
        System.out.println(line);
    }

    public void addEvent(String eventId, String eventName, String eventDesc, String date, String time, String eventVenue){
        //create sqlConnection object
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to insert data into database
        String queryEvent = "insert into event values(" +
                "'" + eventId + "', '" + eventName + "', '" + eventDesc + "', '" + date + "', '" + time + "');";

        String queryVenue = "insert into event_venue values (" +
                "'" + eventId + "', '" + eventVenue + "');";

        sqlConnection.insertData(queryEvent);

        sqlConnection.insertData(queryVenue);
    }

    public boolean isAvailable(String date, String venue) throws SQLException {

        //SqlConnection Class
        SqlConnection sqlConnection = new SqlConnection();

        sqlConnection.startConnection();

        boolean available = true;

        //MySQL query to get event data
        String data = "select * from event_venue join event on event_venue.event_id = event.event_id";

        //execute query and store the data in data ResultSet
        ResultSet getData = sqlConnection.executeQuery(data);

        //check for availability
        while (getData.next()) {
            if (getData.getString("date").equals(date) && getData.getString("venue_id").equals(venue)) {
                available = false;
            }
        }

        return available;
    }

    public ArrayList<Venue> getVenueList() throws SQLException {
        //Create sqlConnection obj
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to get all club data from club table
        String query = "SELECT * FROM venue;";

        //execute query and store the data in data ResultSet
        ResultSet data = sqlConnection.executeQuery(query);

        //create an arrayList
        ArrayList<Venue> clubListArray = new ArrayList<>();

        //add clubs to the clubListArray as club obj(s)
        while (data.next()) {
            //create a temp instance of club
            Venue tempVenue = new Venue(data.getString("venue_id"), data.getString("venue_name"));
            //add the club to the arraylist
            clubListArray.add(tempVenue);
        }

        //return the arraylist
        return clubListArray;

    }

    public ArrayList<Event> getEventList() throws SQLException {
        //Create sqlConnection obj
        SqlConnection sqlConnection = new SqlConnection();

        //establish connection with database
        sqlConnection.startConnection();

        //MySQL query to get all club data from club table
        String query = "SELECT * FROM event;";

        //execute query and store the data in data ResultSet
        ResultSet data = sqlConnection.executeQuery(query);

        //create an arrayList
        ArrayList<Event> eventListArray = new ArrayList<>();

        //add clubs to the clubListArray as club obj(s)
        while (data.next()) {
            //create a temp instance of club
            Event tempEvent = new Event(data.getString("event_id"), data.getString("event_name"), data.getString("event_description"), data.getString("date"), data.getString("time_slot"));
            //add the club to the arraylist
            eventListArray.add(tempEvent);
        }

        //return the arraylist
        return eventListArray;
    }

    public String generateEventId() throws SQLException {
        SqlConnection sqlConnection = new SqlConnection();
        sqlConnection.startConnection();
        String getLatestId = "SELECT * FROM event ORDER BY event_id DESC LIMIT 1";
        ResultSet latestIdResult = sqlConnection.executeQuery(getLatestId);
        String strNextId = "E000";
        while (latestIdResult.next()) {
            String strLatestId = latestIdResult.getNString("event_id");
            strLatestId = strLatestId.substring(1);
            int intLatestId = Integer.parseInt(strLatestId);
            int intNextId = intLatestId + 1;
            strNextId = String.format("e%04d", intNextId);
        }
        sqlConnection.closeConnection();
        return strNextId;
    }

    public ArrayList<Event> searchEventById(String eventId) throws SQLException {
        ArrayList<Event> eventData = getEventList(), eventResult = new ArrayList<Event>();

        int size = 0;

        while (eventResult.size() > size){
            if ((eventData.get(size).getEvent_id().toLowerCase().contains(eventId.toLowerCase()))){
                eventResult.add(eventData.get(size));
                return eventResult;
            }
            size++;
        }

        return eventResult;
    }
}

