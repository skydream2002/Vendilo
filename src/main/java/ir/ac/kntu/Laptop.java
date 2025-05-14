package  ir.ac.kntu;

public class Laptop extends DigitalProduct {
    private String GPUModel;
    private boolean isHaveBluetooth;
    private boolean isHaveWebcam;

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
