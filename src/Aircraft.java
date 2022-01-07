import java.io.File;

public class Aircraft {
    private String make;
    private String model;
    private String tailNumber;
    private double craftWeight;
    private double maximumTakeoffWeight;
    private File layoutFile;

    public Aircraft(String make, String model, String tailNumber, double craftWeight, double maximumTakeoffWeight, File layoutFile) {
        this.make = make;
        this.model = model;
        this.tailNumber = tailNumber;
        this.craftWeight = craftWeight;
        this.maximumTakeoffWeight = maximumTakeoffWeight;
        this.layoutFile = layoutFile;
    }

    @Override
    public String toString() {
        return "Make: " + make + "\n" +
                "Model: " + model + "\n" +
                "Tail Number: " + tailNumber + "\n" +
                "Craft Weight: " + craftWeight + "\n" +
                "Maximum Takeoff Weight: " + maximumTakeoffWeight + "\n";

    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public double getCraftWeight() {
        return craftWeight;
    }

    public void setCraftWeight(double craftWeight) {
        this.craftWeight = craftWeight;
    }

    public double getMaximumTakeoffWeight() {
        return maximumTakeoffWeight;
    }

    public void setMaximumTakeoffWeight(double maximumTakeoffWeight) {
        this.maximumTakeoffWeight = maximumTakeoffWeight;
    }

    public File getLayoutFile() {
        return layoutFile;
    }

    public void setLayoutFile(File layoutFile) {
        this.layoutFile = layoutFile;
    }
}
