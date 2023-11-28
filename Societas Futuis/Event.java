import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date; // java package imported for date -> use like an object
import java.util.Scanner;

public class Event {
    // private attributes of Event including foreign key values
    private int event_id;
    private String event_name;
    private Club club;
    private String event_description;
    private Date date; // Date d = new Date();
    private String event_time;
    private Venue venue;
    private String venue_name;

    // constructor
    public Event (int event_id, String event_name, Club club, String event_description, Date date, String event_time, Venue venue){
        this.event_id = event_id;
        this.event_name = event_name;
        this.club = club;
        this.event_description = event_description;
        this.date = date; // date set krna kalle thama kela wune
        this.event_time = event_time;
        this.venue = venue;
    }

    // date set krna magula thama bri
    // eka hduwoth athi
    // delete the dumb comments I made

    // getter and setter methods for the private attributes
    // event id:
    public int getEvent_id() {
        return event_id;
    }
    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    // event name:
    public String getEvent_name() {
        return event_name;
    }
    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    // club:
    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }

    // event description:
    public String getEvent_description() {
        return event_description;
    }
    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    // date:
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date =date;
    }

    // event time:
    public String getEvent_time(){
        return event_time;
    }
    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    // venue
    public Venue getVenue() {
        return venue;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void createEvent(SqlConnection sqlConnection) {
        // scanner class
        Scanner scanner = new Scanner(System.in);

        // Step 1: Check if the club exists in the database
        System.out.println("Enter the club name: ");
        String club_name = scanner.nextLine();

        // getClubByName method is there in SQLConnection class
        Club club = sqlConnection.getClubByName(club_name);

        if (club == null) {
            System.out.println("Club does not exist. Please enter a valid club name.");
            return; // Exit the method if it doesn't exist
        }

        // Step 2: Check the availability
        /*System.out.println("Enter the date (yyyy-mm-dd): ");
        String dateString = scanner.nextLine();

        String pattern = "yyyy-MM-dd";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        try {
            Date date = dateFormat.parse(dateString);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the parse exception as needed
        }*/
        System.out.println("Enter the time:");
        event_time = scanner.nextLine();

        System.out.println("Enter the venue:");
        String venue_name = null;
        Venue venue = sqlConnection.getVenueByName(venue_name);
        venue_name = scanner.nextLine();
        if (venue == null)
            System.out.println("Venue is available to book");
        if (!sqlConnection.isEventTimeSlotAvailable(club.getClub_id(), date, event_time, venue)) {
            System.out.println("Sorry, the chosen time and venue are not available. Please choose another.");
            return;  // Exit the method if the time slot is not available
        }

        // step 3: Add event name and details
        System.out.println("Enter event name: ");
        event_name = scanner.nextLine();

        System.out.println("Enter event description: ");
        event_description = scanner.nextLine();

        // Step 4: confirm and save event
        System.out.println("Event Details: \n + " +
                "Event Name: " + event_name + "\n" +
                "Club: " + club.getClub_id() + "\n" +
                "Event Details: : " + event_description + "\n" +
                "Date: " + date + "\n" +
                "Time: " + event_time + "\n" +
                "Venue: " + venue_name + "\n" );
        // event_id is auto incremented
        System.out.println("Do you want to confirm and save the event? (y/n)");
        String confirmation = scanner.nextLine().toLowerCase();

        if (confirmation.equals("y")) {
            // save to the database
            sqlConnection.insertEvent(this);
            System.out.println("Event saved successfully in the database");
        } else {
            System.out.println("Event not saved");
        }
    }

    public void updateEvent(SqlConnection sqlConnection){
        Scanner scanner = new Scanner(System.in);

        // display current event descriptions and details:
        System.out.println("Event Details: \n + " +
                "Event Name: " + event_name + "\n" +
                "Club: " + club.getClub_id() + "\n" +
                "Event Details: : " + event_description + "\n" +
                "Date: " + date + "\n" +
                "Time: " + event_time + "\n" +
                "Venue: " + venue_name + "\n" );

        System.out.println("Do you want to update the event? (y/n");
        String confirmation = scanner.nextLine().toLowerCase();

        if (confirmation.equals("y")) {
            // ask user which details they want to update
            System.out.println("What would you like to update?\n" +
                    "1. Event Name\n" +
                    "2. Event Details\n" +
                    "3. Date\n" +
                    "4. Time\n" +
                    "5. Venue\n" +
                    "Enter the corresponding number:");

            int selection = scanner.nextInt();
            scanner.nextLine();

            switch(selection) {
                case 1:
                    System.out.println("Enter the new event name:");
                    event_name = scanner.nextLine();
                    break;
                case 2:
                    System.out.println("Enter the new event details:");
                    event_description = scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Enter the new date (yyyy-mm-dd):");
                    String newDateString = scanner.nextLine();
                    // Parse the date string into a Date object (you may want to handle exceptions here)
                    //date = java.sql.Date.valueOf(newDateString).toLocalDate();
                    break;
                case 4:
                    System.out.println("Enter the new time:");
                    event_time = scanner.nextLine();
                    break;
                case 5:
                    System.out.println("Enter the new venue:");
                    venue_name = scanner.nextLine();
                    break;
            }

            // update the event in the database
            sqlConnection.updateEvent(this);
            System.out.println("Event updated successfully");
        } else {
            System.out.println("Event not updated");
        }

    }

    public void deleteEvent(SqlConnection sqlConnection){
        /*
        * Since this is deleting like an idiot
        * All the work is in SqlConnection class
        * method deleteEvent()
        * */
        Scanner scanner = new Scanner(System.in);

        // display current event details
        System.out.println("Event Details: \n + " +
                "Event ID: " + getEvent_id() + "\n" +
                "Event Name: " + event_name + "\n" +
                "Club: " + club.getClub_id() + "\n" +
                "Event Details: : " + event_description + "\n" +
                "Date: " + date + "\n" +
                "Time: " + event_time + "\n" +
                "Venue: " + venue_name + "\n" );

        // Ask the user if they want to delete the event
        System.out.println("Do you want to delete this event? (y/n)");
        String confirmation = scanner.nextLine().toLowerCase();

        if (confirmation.equals("yes")) {
            // Delete the event from the database
            sqlConnection.deleteEvent(this);
            System.out.println("Event deleted successfully!");
        } else {
            System.out.println("Event not deleted.");
        }
    }
}
