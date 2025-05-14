package ir.ac.kntu;

public class Address {
    private String title;
    private String Province;
    private String city;
    private String description;
    
    public Address() {
    }
    public Address(String title, String province, String city) {
        this.title = title;
        Province = province;
        this.city = city;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getProvince() {
        return Province;
    }
    public void setProvince(String province) {
        Province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}

