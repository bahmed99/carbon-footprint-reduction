package projet.gl.server.sale;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private Long id;
    private Double price;
    private LocalDate dateSale;
    private LocalDate dateDelivery;
    private LocalDate dateExpiratonInsurance;
    private Long vehicleId;
    private String modelName;
    private String brandName;

    public SaleDTO(Sale sale) {
        this.id = sale.getId();
        this.price = sale.getPrice();
        this.dateSale = sale.getDateSale();
        this.dateDelivery = sale.getDateDelivery();
        this.dateExpiratonInsurance = sale.getDateExpiratonInsurance();
        this.vehicleId = sale.getVehicleId();
        this.modelName = sale.getModelName();
        this.brandName = sale.getBrandName();

    }

}