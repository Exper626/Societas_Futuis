public class Event {

    /* Contribution - Azhar, Hanaa 20221313/2237922 */

    // private attributes of Event including foreign key values
    private String event_id;
    private String event_name;
    private String event_description;
    private String date;
    private String event_time;

    public Event(String event_id, String event_name, String event_description, String date, String event_time){
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_description = event_description;
        this.event_time = event_time;
        this.date = date;
    }


    // getter and setter methods for the private attributes
    public String getEvent_id() {
        return event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_description() {
        return event_description;
    }

    public String getDate() {
        return date;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }
}
