package projet.gl.server.vehicle;

import java.util.List;

public class BrandModelCountDTO {
    private String brandName;
    private List<ModelCountDTO> models;

    public BrandModelCountDTO() {
    }

    public BrandModelCountDTO(String brandName, List<ModelCountDTO> models) {
        this.brandName = brandName;
        this.models = models;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<ModelCountDTO> getModels() {
        return models;
    }

    public void setModels(List<ModelCountDTO> models) {
        this.models = models;
    }
}