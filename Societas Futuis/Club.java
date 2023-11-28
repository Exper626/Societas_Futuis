public class Club {
    private String club_id; // primary key attribute
    private String club_name; // attribute

    // constructor
    Club(String club_id, String club_name){
        this.club_id = club_id;
        this.club_name = club_name;
    }

    // getter method for clubID in order to access:
    public String getClub_id(){
        return club_id;
    }
    // getter method for club_name in order to access:
    public String getClub_name(){
        return club_name;
    }

    // setter method for clubID in order to set in attributes value:
    public void setClub_id(String club_id){
        this.club_id = club_id;
    }
    // setter method for club_name in order to set in attributes value:
    public void setClubName(String club_name){
        this.club_name = club_name;
    }

}
