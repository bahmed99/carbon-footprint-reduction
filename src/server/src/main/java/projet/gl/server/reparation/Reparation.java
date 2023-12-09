package projet.gl.server.reparation;

import java.time.LocalDate;
import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import projet.gl.server.vehicle.Vehicle;

@AllArgsConstructor
@Builder
@Entity
@Table(name = "Reparation")
public class Reparation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "repair_cost", nullable = false)
    private Double repairCost;

    @Column(name = "repair_start_date")
    private LocalDate repairStartDate;

    @Column(name = "repair_end_date")
    private LocalDate repairEndDate;

    @Column(name = "repair_created_at")
    private LocalDate repairCreatedAt;

    @Column(name = "repair_updated_at")
    private LocalDate repairUpdatedAt;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Vehicle repairedVehicle;

    public Reparation() {

    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Double return the repairCost
     */
    public Double getRepairCost() {
        return repairCost;
    }

    /**
     * @param repairCost the repairCost to set
     */
    public void setRepairCost(Double repairCost) {
        this.repairCost = repairCost;
    }

    /**
     * @return LocalDate return the repairStartDate
     */
    public LocalDate getRepairStartDate() {
        return repairStartDate;
    }

    /**
     * @param repairStartDate the repairStartDate to set
     */
    public void setRepairStartDate(LocalDate repairStartDate) {
        this.repairStartDate = repairStartDate;
    }

    /**
     * @return LocalDate return the repairEndDate
     */
    public LocalDate getRepairEndDate() {
        return repairEndDate;
    }

    /**
     * @param repairEndDate the repairEndDate to set
     */
    public void setRepairEndDate(LocalDate repairEndDate) {
        this.repairEndDate = repairEndDate;
    }

    /**
     * @return LocalDate return the repairCreatedAt
     */
    public LocalDate getRepairCreatedAt() {
        return repairCreatedAt;
    }

    /**
     * @param repairCreatedAt the repairCreatedAt to set
     */
    public void setRepairCreatedAt(LocalDate repairCreatedAt) {
        this.repairCreatedAt = repairCreatedAt;
    }

    /**
     * @return LocalDate return the repairUpdatedAt
     */
    public LocalDate getRepairUpdatedAt() {
        return repairUpdatedAt;
    }

    /**
     * @param repairUpdatedAt the repairUpdatedAt to set
     */
    public void setRepairUpdatedAt(LocalDate repairUpdatedAt) {
        this.repairUpdatedAt = repairUpdatedAt;
    }

    /**
     * @return Long return the vehicleId
     */
    public Long getVehicleId() {
        return vehicleId;
    }

    /**
     * @param vehicleId the vehicleId to set
     */
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * @return Vehicle return the repairedVehicle
     */
    public Vehicle getRepairedVehicle() {
        return repairedVehicle;
    }

    /**
     * @param repairedVehicle the repairedVehicle to set
     */
    public void setRepairedVehicle(Vehicle repairedVehicle) {
        this.repairedVehicle = repairedVehicle;
    }

    @PrePersist
    public void prePersist() {
        Random random = new Random();
        repairStartDate = LocalDate.now();
        repairCreatedAt = LocalDate.now();
        repairUpdatedAt = LocalDate.now();
        repairEndDate = LocalDate.now().plusDays(random.nextInt(30));
    }

    public String getModelName() {
        return repairedVehicle.getModel().getName();
    }

    public String getBrandName() {
        return repairedVehicle.getModel().getBrand().getName();
    }

}
