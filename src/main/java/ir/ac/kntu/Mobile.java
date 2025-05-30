package ir.ac.kntu;

import java.util.Scanner;

public class Mobile extends DigitalProduct {
    private String frontCamera;
    private String rearCamera;
    private String internetNetwork;

    public Mobile() {
        super.setType(ProductType.DIGITAL_PRODUCT);
    }

    @Override
    public void showDetails() {
        System.out.println("{----Product Details (Mobile)----");
        System.out.println("Name : " + getName());
        System.out.println("Category : " + getType());
        System.out.println("Brand : " + getBrand());
        System.out.println("Price : " + getPrice());
        System.out.println("Seller's Name : " + getSeller().getFirstName() + " " + getSeller().getLastName());
        System.out.println("Rating Average : " + getAverageRating());
        System.out.println("Front Camera : " + getFrontCamera());
        System.out.println("Rear Camera : " + getRearCamera());
        System.out.println("Network : " + getInternetNetwork());
        System.out.println("Storage : " + getStorage());
        System.out.println("RAM : " + getRAM() + "}");
        System.out.println("Stock : " + getStock());
    }

    @Override
    public void addProduct(Scanner scanner, Seller seller) {
        super.setSeller(seller);
        System.out.println("Enter name :");
        String name = scanner.nextLine();
        super.setName(name);
        System.out.println("Brand : ");
        String brand = scanner.nextLine();
        super.setBrand(brand);
        System.out.println("Price :");
        double price = scanner.nextDouble();
        scanner.nextLine();
        super.setPrice(price);
        System.out.println("Stock :");
        int stock = scanner.nextInt();
        scanner.nextLine();
        super.setStock(stock);
        System.out.println("Front Camera Resolution");
        String front = scanner.nextLine();
        setFrontCamera(front);
        System.out.println("Rear Camera Resolution :");
        String rear = scanner.nextLine();
        setRearCamera(rear);
        System.out.println("Internet Network");
        String network = scanner.nextLine();
        setInternetNetwork(network);
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
    }

    public String getRearCamera() {
        return rearCamera;
    }

    public void setRearCamera(String rearCamera) {
        this.rearCamera = rearCamera;
    }

    public String getInternetNetwork() {
        return internetNetwork;
    }

    public void setInternetNetwork(String internetNetwork) {
        this.internetNetwork = internetNetwork;
    }
}
