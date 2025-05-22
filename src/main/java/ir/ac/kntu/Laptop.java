package ir.ac.kntu;

import java.util.Scanner;

public class Laptop extends DigitalProduct {
    private String GPUModel;
    private boolean hasHaveBluetooth;
    private boolean hasHaveWebcam;

    public Laptop() {
        super.setType(ProductType.DIGITAL_PRODUCT);
    }

    @Override
    public void showDetails() {
        System.out.println("{----Product Details (Laptop)----");
        System.out.println("Name : " + getName());
        System.out.println("Category : " + getType());
        System.out.println("Brand : " + getBrand());
        System.out.println("Price : " + getPrice());
        System.out.println("Seller's Name : " + getSeller().getFirstName() + " " + getSeller().getLastName());
        System.out.println("Rating Average : " + getAverageRating());
        System.out.println("GPU : " + getGPUModel());
        System.out.println("Bluetooth : " + isHasHaveBluetooth());
        System.out.println("Webcam : " + isHasHaveWebcam());
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
        System.out.println("GPU model :");
        String GPU = scanner.nextLine();
        setGPUModel(GPU);
        System.out.println("Bluetooth : (true/false)");
        boolean bluetooth = scanner.nextBoolean();
        setHasHaveBluetooth(bluetooth);
        System.out.println("Webcam : (true/false)");
        boolean webcam = scanner.nextBoolean();
        setHasHaveWebcam(webcam);
    }

    public String getGPUModel() {
        return GPUModel;
    }

    public void setGPUModel(String GPUModel) {
        this.GPUModel = GPUModel;
    }

    public boolean isHasHaveBluetooth() {
        return hasHaveBluetooth;
    }

    public void setHasHaveBluetooth(boolean isHaveBluetooth) {
        this.hasHaveBluetooth = isHaveBluetooth;
    }

    public boolean isHasHaveWebcam() {
        return hasHaveWebcam;
    }

    public void setHasHaveWebcam(boolean isHaveWebcam) {
        this.hasHaveWebcam = isHaveWebcam;
    }

}
