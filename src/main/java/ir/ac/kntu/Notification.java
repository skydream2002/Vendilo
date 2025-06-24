package ir.ac.kntu;

public class Notification {
    private CustomerSupportRequest request;
    private String topic;
    private String details;

    public Notification() {
    }

    public Notification(String topic, String details) {
        this.details = details;
        this.topic = topic;
    }
    public CustomerSupportRequest getRequest() {
        return request;
    }

    public void setRequest(CustomerSupportRequest request) {
        this.request = request;
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

    @Override
    public String toString() {
        return "topic : " + topic + ", details : " + details;
    }
}
