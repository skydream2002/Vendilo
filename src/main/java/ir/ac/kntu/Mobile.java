package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import java.util.Scanner;

public class Mobile extends DigitalProduct {
    private String frontCamera;
    private String rearCamera;
    private String internetNetwork;

    public Mobile() {
        super.setType(ProductType.DIGITAL);
    }

    @Override
    public void showDetails() {
        System.out.println("----Product Details (Mobile)----");
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
        System.out.println("RAM : " + getRam());
        System.out.println("Stock : " + getStock());
    }

    @Override
    public void addProduct(Scanner scanner, Seller seller) {
        setBasicInfo(scanner, seller);
        setCameraInfo(scanner);
        setNetworkInfo(scanner);
    }

    private void setBasicInfo(Scanner scanner, Seller seller) {
        super.setSeller(seller);
        System.out.println("Enter name:");
        super.setName(scanner.nextLine());

        System.out.println("Brand:");
        super.setBrand(scanner.nextLine());

        System.out.println("Price:");
        while (true) {
            double price = SafeInput.getDouble(scanner);
            if (price > 0) {
                super.setPrice(price);
                break;
            } else {
                System.out.println("Price must be positive.");
            }
        }

        System.out.println("Stock:");
        while (true) {
            int stock = SafeInput.getInt(scanner);
            if (stock > 0) {
                super.setStock(stock);
                break;
            } else {
                System.out.println("Stock must be positive.");
            }
        }

        System.out.println("RAM :");
        super.setRam(scanner.nextLine());

        System.out.println("Storage :");
        super.setStorage(scanner.nextLine());
    }

    private void setCameraInfo(Scanner scanner) {
        System.out.println("Front Camera Resolution:");
        setFrontCamera(scanner.nextLine());

        System.out.println("Rear Camera Resolution:");
        setRearCamera(scanner.nextLine());
    }

    private void setNetworkInfo(Scanner scanner) {
        System.out.println("Internet Network:");
        setInternetNetwork(scanner.nextLine());
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
