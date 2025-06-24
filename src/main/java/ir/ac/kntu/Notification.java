package ir.ac.kntu;

public class Notification {
    private String topic;
    private String details;

    public Notification() {
    }

    public Notification(String details, String topic) {
        this.details = details;
        this.topic = topic;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    
}
