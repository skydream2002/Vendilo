package ir.ac.kntu;

public class Mobile extends DigitalProduct {
    private String frontCameraResolution;
    private String rearCameraResolution;
    private String internetNetwork;

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
