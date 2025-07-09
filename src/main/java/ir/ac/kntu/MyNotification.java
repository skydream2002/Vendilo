package ir.ac.kntu;

public class MyNotification {
    private Product product;
    private CustomerSupportRequest request;
    private String topic;
    private String details;

    public MyNotification() {
    }

    public MyNotification(String topic, String details, Product product) {
        this.product = product;
        this.topic = topic;
        this.details = details;
    }

    public MyNotification(String topic, String details) {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
