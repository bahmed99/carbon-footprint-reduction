package projet.gl.server.rental;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import projet.gl.server.vehicle.Vehicle;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.JoinColumn;

@AllArgsConstructor
@Builder
@Entity
@Table(name = "Rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rental_fee", nullable = false)
    private Double rentalFee;

    @Column(name = "rental_start_date")
    @Temporal(TemporalType.DATE)
    private LocalDate rentalStartDate;

    @Column(name = "rental_end_date")
    @Temporal(TemporalType.DATE)
    private LocalDate rentalEndDate;

    @Column(name = "insurance_expiration_date")
    @Temporal(TemporalType.DATE)
    private LocalDate insuranceExpirationDate;

    @Column(name = "rental_created_at")
    @Temporal(TemporalType.DATE)
    private LocalDate rentalCreatedAt;

    @Column(name = "rental_updated_at")
    @Temporal(TemporalType.DATE)
    private LocalDate rentalUpdatedAt;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Vehicle rentedVehicle;

    public Rental() {

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
     * @return Double return the rentalFee
     */
    public Double getRentalFee() {
        return rentalFee;
    }

    /**
     * @param rentalFee the rentalFee to set
     */
    public void setRentalFee(Double rentalFee) {
        this.rentalFee = rentalFee;
    }

    /**
     * @return LocalDate return the rentalStartDate
     */
    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    /**
     * @param rentalStartDate the rentalStartDate to set
     */
    public void setRentalStartDate(LocalDate rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    /**
     * @return LocalDate return the rentalEndDate
     */
    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }

    /**
     * @param rentalEndDate the rentalEndDate to set
     */
    public void setRentalEndDate(LocalDate rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    /**
     * @return LocalDate return the insuranceExpirationDate
     */
    public LocalDate getInsuranceExpirationDate() {
        return insuranceExpirationDate;
    }

    /**
     * @param insuranceExpirationDate the insuranceExpirationDate to set
     */
    public void setInsuranceExpirationDate(LocalDate insuranceExpirationDate) {
        this.insuranceExpirationDate = insuranceExpirationDate;
    }

    /**
     * @return LocalDate return the rentalCreatedAt
     */
    public LocalDate getRentalCreatedAt() {
        return rentalCreatedAt;
    }

    /**
     * @param rentalCreatedAt the rentalCreatedAt to set
     */
    public void setRentalCreatedAt(LocalDate rentalCreatedAt) {
        this.rentalCreatedAt = rentalCreatedAt;
    }

    /**
     * @return LocalDate return the rentalUpdatedAt
     */
    public LocalDate getRentalUpdatedAt() {
        return rentalUpdatedAt;
    }

    /**
     * @param rentalUpdatedAt the rentalUpdatedAt to set
     */
    public void setRentalUpdatedAt(LocalDate rentalUpdatedAt) {
        this.rentalUpdatedAt = rentalUpdatedAt;
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
     * @return Vehicle return the rentedVehicle
     */
    public Vehicle getRentedVehicle() {
        return rentedVehicle;
    }

    /**
     * @param rentedVehicle the rentedVehicle to set
     */
    public void setRentedVehicle(Vehicle rentedVehicle) {
        this.rentedVehicle = rentedVehicle;
    }

    @PrePersist
    public void prePersist() {
        Random random = new Random();
        rentalStartDate = LocalDate.now();
        rentalCreatedAt = LocalDate.now();
        rentalUpdatedAt = LocalDate.now();
        rentalEndDate =  LocalDate.now().plusDays(random.nextInt(30) + 1);
        insuranceExpirationDate = LocalDate.now().plusYears(random.nextInt(4) + 1);
    }

    @PreUpdate
    public void preUpdate() {
        rentalUpdatedAt = LocalDate.now();
    }

}