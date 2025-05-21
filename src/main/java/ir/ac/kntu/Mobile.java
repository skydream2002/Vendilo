package ir.ac.kntu;

public class Mobile extends DigitalProduct {
    private String frontCameraResolution;
    private String rearCameraResolution;
    private String internetNetwork;

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
