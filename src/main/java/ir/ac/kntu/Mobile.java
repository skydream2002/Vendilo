package ir.ac.kntu;

import java.util.Scanner;

public class Mobile extends DigitalProduct {
    private String frontCameraResolution;
    private String rearCameraResolution;
    private String internetNetwork;

    @Override
    public void showDetails() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
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

            System.out.println("-----1.back------");
            int choice = scanner.nextInt();
            if (choice == 1) {
                return;
            } else {
                System.out.println("invalid choice");
            }
        }
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
