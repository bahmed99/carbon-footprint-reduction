package projet.gl.server.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id)
                .map(sale -> new ResponseEntity<>(sale, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO) {
        Sale sale = convertToSale(saleDTO);
        Sale createdSale = saleService.createSale(sale);
        return new ResponseEntity<>(convertToSaleDTO(createdSale), HttpStatus.CREATED);
    }

    private SaleDTO convertToSaleDTO(Sale sale) {
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setId(sale.getId());
        saleDTO.setPrice(sale.getPrice());
        saleDTO.setDateSale(sale.getDateSale());
        saleDTO.setDateDilevery(sale.getDateDilevery());
        saleDTO.setDateExpiratonInsurance(sale.getDateExpiratonInsurance());
        saleDTO.setCreatedAt(sale.getCreatedAt());
        saleDTO.setUpdatedAt(sale.getUpdatedAt());
        saleDTO.setVehicleId(sale.getVehicleId());
        return saleDTO;
    }

    private Sale convertToSale(SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setId(saleDTO.getId());
        sale.setPrice(saleDTO.getPrice());
        sale.setDateDilevery(saleDTO.getDateDilevery());
        sale.setDateExpiratonInsurance(saleDTO.getDateExpiratonInsurance());
        sale.setVehicleId(saleDTO.getVehicleId());
        return sale;
    }
}
