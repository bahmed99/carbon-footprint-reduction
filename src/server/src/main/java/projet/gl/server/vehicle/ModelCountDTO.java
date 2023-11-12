package projet.gl.server.vehicle;

public class ModelCountDTO {
    private String modelName;
    private long vehicleCount;

    public ModelCountDTO() {
    }

    public ModelCountDTO(String modelName, long vehicleCount) {
        this.modelName = modelName;
        this.vehicleCount = vehicleCount;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public long getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(long vehicleCount) {
        this.vehicleCount = vehicleCount;
    }
}
