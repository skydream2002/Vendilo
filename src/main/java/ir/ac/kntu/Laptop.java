package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import java.util.Scanner;

public class Laptop extends DigitalProduct {
    private String gpuModel;
    private boolean hasHaveBluetooth;
    private boolean hasHaveWebcam;

    public Laptop() {
        super.setType(ProductType.DIGITAL);
    }

    @Override
    public void showDetails(User user) {
        double finalPrice = getPrice();
        if (user instanceof Customer customer) {
            finalPrice = customer.getVendoliPlus().isActive()
                        ? getPrice() * 0.95
                        : getPrice();
        }
        System.out.println("----Product Details (Laptop)----");
        System.out.println("Name : " + getName());
        System.out.println("Category : " + getType());
        System.out.println("Brand : " + getBrand());
        System.out.println("Price : " + finalPrice);
        System.out.println("Seller's Name : " + getSeller().getFirstName() + " " + getSeller().getLastName());
        System.out.println("Rating Average : " + getAverageRating());
        System.out.println("GPU : " + getGPUModel());
        System.out.println("Bluetooth : " + isHasHaveBluetooth());
        System.out.println("Webcam : " + isHasHaveWebcam());
        System.out.println("Storage : " + getStorage());
        System.out.println("RAM : " + getRam());
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

    private void readAndSetLaptopSpecificInfo(Scanner scanner) {
        System.out.println("GPU model:");
        setGpuModel(scanner.nextLine());

        System.out.println("Bluetooth (true/false):");
        setHasHaveBluetooth(SafeInput.getBooleanInput(scanner));

        System.out.println("Webcam (true/false):");
        setHasHaveWebcam(SafeInput.getBooleanInput(scanner));
    }

    public String getGPUModel() {
        return gpuModel;
    }

    public void setGpuModel(String gpuModel) {
        this.gpuModel = gpuModel;
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
