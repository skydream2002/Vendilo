package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {
    private String agencyCode;
    private String storeName;
    private int nationalCode;
    private String province;
    private boolean isVerified;
    private List<Product> products = new ArrayList<>();

    public void addProduct() {

    }

    public void handleVerificationRequests() {

    }

    public void respondToSupportRequest(){
        
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(int nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
