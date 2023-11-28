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
    private LocalDate repairCreatedAt;
    private LocalDate repairUpdatedAt;
    private Long vehicleId;

    public ReparationDTO(Reparation reparation) {
        this.id = reparation.getId();
        this.repairCost = reparation.getRepairCost();
        this.repairStartDate = reparation.getRepairStartDate();
        this.repairEndDate = reparation.getRepairEndDate();
        this.repairCreatedAt = reparation.getRepairCreatedAt();
        this.repairUpdatedAt = reparation.getRepairUpdatedAt();
        this.vehicleId = reparation.getVehicleId();
    }
}
