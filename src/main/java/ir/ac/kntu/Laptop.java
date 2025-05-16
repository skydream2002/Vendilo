package  ir.ac.kntu;

public class Laptop extends DigitalProduct {
    private String GPUModel;
    private boolean isHaveBluetooth;
    private boolean isHaveWebcam;

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
        System.out.println("Bluetooth : " + isIsHaveBluetooth());
        System.out.println("Webcam : " + isIsHaveWebcam());
        System.out.println("Storage : " + getStorage());
        System.out.println("RAM : " + getRAM());
    }

    public String getGPUModel() {
        return GPUModel;
    }

    public void setGPUModel(String GPUModel) {
        this.GPUModel = GPUModel;
    }

    public boolean isIsHaveBluetooth() {
        return isHaveBluetooth;
    }

    public void setIsHaveBluetooth(boolean isHaveBluetooth) {
        this.isHaveBluetooth = isHaveBluetooth;
    }

    public boolean isIsHaveWebcam() {
        return isHaveWebcam;
    }

    public void setIsHaveWebcam(boolean isHaveWebcam) {
        this.isHaveWebcam = isHaveWebcam;
    }

}
