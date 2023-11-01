package projet.gl.server.vehicle;

import java.util.List;

public class VehiculeFilterDTO {
    private List<Long> colorIds;
    private List<Long> modelIds;
    private List<Long> configurationIds;

    public VehiculeFilterDTO() {
    }

    public VehiculeFilterDTO(List<Long> colorIds, List<Long> modelIds, List<Long> configurationIds) {
        this.colorIds = colorIds;
        this.modelIds = modelIds;
        this.configurationIds = configurationIds;
    }

    public List<Long> getColorIds() {
        return colorIds;
    }

    public void setColorIds(List<Long> colorIds) {
        this.colorIds = colorIds;
    }

    public List<Long> getModelIds() {
        return modelIds;
    }

    public void setModelIds(List<Long> modelIds) {
        this.modelIds = modelIds;
    }

    public List<Long> getConfigurationIds() {
        return configurationIds;
    }

    public void setConfigurationIds(List<Long> configurationIds) {
        this.configurationIds = configurationIds;
    }
}
