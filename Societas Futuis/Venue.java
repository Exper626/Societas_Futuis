public class Venue {
    // private attributes of the venue
    private String venue_id; //primary key
    private String venue_name;

    // constructor of the venue
    Venue (String venue_id, String venue_name){
        this.venue_id = venue_id;
        this.venue_name = venue_name;
    }

    // getter method for Venue_id:
    public String getVenue_id(){
        return venue_id;
    }
    // getter method for Venue_name:
    public String getVenue_name(){
        return venue_name;
    }

    // setter method for venue id
    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    // setter method for venue name
    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }
}
