package projet.gl.server.vehicle;

import java.util.List;

public class BrandModelCountDTO {
    private String brandName;
    private Long brandId;
    private List<ModelCountDTO> models;



    public BrandModelCountDTO() {
    }

    public BrandModelCountDTO(String brandName,long brandId, List<ModelCountDTO> models) {
        this.brandName = brandName;
        this.brandId = brandId;
        this.models = models;
    }

    public Long getBrandId(){
        return brandId;
    }

    public void setBrandId(Long brandId){
        this.brandId = brandId;
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