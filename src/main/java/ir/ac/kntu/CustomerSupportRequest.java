package ir.ac.kntu;

public class CustomerSupportRequest {

    private Customer customer;
    private String category;
    private String message;
    private String status; // "submitted", "in progress", "closed"
    private String supportResponse;

    public CustomerSupportRequest(String category, String message, Customer customer) {
        this.category = category;
        this.message = message;
        this.status = "submitted";
        this.customer = customer;
    }

    public String getCategory() {
        return category;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getSupportResponse() {
        return supportResponse;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSupportResponse(String response) {
        this.supportResponse = response;
    }

    public Customer getCustomer() {
        return customer;
    }
}
