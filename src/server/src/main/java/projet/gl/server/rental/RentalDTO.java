package projet.gl.server.rental;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {

    private Long id;
    private Double rentalFee;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private LocalDate insuranceExpirationDate;
    private LocalDate rentalCreatedAt;
    private LocalDate rentalUpdatedAt;
    private Long vehicleId;
    private String modelName;
    private String brandName;

    public RentalDTO(Rental rental) {
        this.id = rental.getId();
        this.rentalFee = rental.getRentalFee();
        this.rentalStartDate = rental.getRentalStartDate();
        this.rentalEndDate = rental.getRentalEndDate();
        this.insuranceExpirationDate = rental.getInsuranceExpirationDate();
        this.vehicleId = rental.getVehicleId();
        this.modelName = rental.getModelName();
        this.brandName = rental.getBrandName();
    }

}
