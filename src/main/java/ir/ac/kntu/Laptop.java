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
        readAndSetBasicProductInfo(scanner);
        readAndSetLaptopSpecificInfo(scanner);
    }

    private void readAndSetBasicProductInfo(Scanner scanner) {
        System.out.println("Enter name:");
        super.setName(scanner.nextLine());

        System.out.println("Brand:");
        super.setBrand(scanner.nextLine());

        System.out.println("Price:");
        super.setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Stock:");
        super.setStock(scanner.nextInt());
        scanner.nextLine();
    }

    private void readAndSetLaptopSpecificInfo(Scanner scanner) {
        System.out.println("GPU model:");
        setGpuModel(scanner.nextLine());

        System.out.println("Bluetooth (true/false):");
        setHasHaveBluetooth(scanner.nextBoolean());

        System.out.println("Webcam (true/false):");
        setHasHaveWebcam(scanner.nextBoolean());
    }

    public String getGPUModel() {
        return GPUModel;
    }

    public void setGpuModel(String GPUModel) {
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
