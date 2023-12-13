package projet.gl.server.reparation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReparationDTO {
    private Long id;
    private Double repairCost;
    private LocalDate repairStartDate;
    private LocalDate repairEndDate;
    private Long vehicleId;
    private String modelName;
    private String brandName;

    public ReparationDTO(Reparation reparation) {
        this.id = reparation.getId();
        this.repairCost = reparation.getRepairCost();
        this.repairStartDate = reparation.getRepairStartDate();
        this.repairEndDate = reparation.getRepairEndDate();
        this.vehicleId = reparation.getVehicleId();
        this.modelName = reparation.getModelName();
        this.brandName = reparation.getBrandName();
    }
}
