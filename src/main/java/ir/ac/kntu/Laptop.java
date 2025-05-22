package ir.ac.kntu;

public class Laptop extends DigitalProduct {
    private String GPUModel;
    private boolean hasHaveBluetooth;
    private boolean hasHaveWebcam;

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
