package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Seller extends User {
    private String agencyCode;
    private String storeName;
    private int nationalCode;
    private String province;
    private boolean isVerified;
    private List<Product> products = new ArrayList<>();
    
    @Override
    public void usersMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) { 
            System.out.println("----Seller Menu----");
            System.out.println("--1.Veiw Products--");
            System.out.println("---2.Add Product---");
            System.out.println("------3.Wallet-----");
            System.out.println("------4.Orders-----");
            System.out.println("-------5.back------");
            System.out.println("choose:");
            int choice = scanner.nextInt();
            // complete this
            switch (choice) {
                case 1 -> System.out.println("view products");
                case 2 -> System.out.println("add product");
                case 3 -> System.out.println("wallet");
                case 4 -> System.out.println("orders");
                case 6 -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("invalid choice");
            }
        }

    }

    public void addProduct() {

    }

    public void handleVerificationRequests() {

    }

    public void respondToSupportRequest(){
        
    }

    public Seller(String email, String firstName, String lastName, String password, int phoneNumber) {
        super(email, firstName, lastName, password, phoneNumber);
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
