package ir.ac.kntu;

import java.util.Scanner;

public class Mobile extends DigitalProduct {
    private String frontCameraResolution;
    private String rearCameraResolution;
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
        System.out.println("Front Camera : " + getFrontCameraResolution());
        System.out.println("Rear Camera : " + getRearCameraResolution());
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
        String frontCamera = scanner.nextLine();
        setFrontCameraResolution(frontCamera);
        System.out.println("Rear Camera Resolution :");
        String rearCamera = scanner.nextLine();
        setRearCameraResolution(rearCamera);
        System.out.println("Internet Network");
        String network = scanner.nextLine();
        setInternetNetwork(network);
    }

    public String getFrontCameraResolution() {
        return frontCameraResolution;
    }

    public void setFrontCameraResolution(String frontCameraResolution) {
        this.frontCameraResolution = frontCameraResolution;
    }

    public String getRearCameraResolution() {
        return rearCameraResolution;
    }

    public void setRearCameraResolution(String rearCameraResolution) {
        this.rearCameraResolution = rearCameraResolution;
    }

    public String getInternetNetwork() {
        return internetNetwork;
    }

    public void setInternetNetwork(String internetNetwork) {
        this.internetNetwork = internetNetwork;
    }
}
