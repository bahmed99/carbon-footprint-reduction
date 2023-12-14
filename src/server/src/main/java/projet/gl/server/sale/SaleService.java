package projet.gl.server.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SaleService {
    private final Random random = new Random();
    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    // Méthode pour obtenir tous les sales
    public List<SaleDTO> getAllSalesDTO() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .map(this::convertToSaleDTO)
                .collect(Collectors.toList());
    }

    // convertir de Sale à SaleDTO
    private SaleDTO convertToSaleDTO(Sale sale) {
        return new SaleDTO(sale);
    }

    // Méthode pour obtenir les sales par page et par taille
    public Page<SaleDTO> getSalesByPageAndSize(Pageable pageable) {
        Page<Sale> sales = saleRepository.findAll(pageable);
        return sales.map(this::convertToSaleDTO);
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public Sale createSale(long idVehicle, double price) {
        Sale sale = new Sale();
        sale.setVehicleId(idVehicle);
        sale.setPrice(price * 1.2);
        sale.setDateDelivery(LocalDate.now().plusDays(random.nextLong(30) + 1));
        sale.setDateExpiratonInsurance(LocalDate.now().plusYears(random.nextLong(4) + 1));
        return saleRepository.save(sale);
    }

    // Méthode pour obtenir la taille de la table de sales
    public long getSalesTableSize() {
        return saleRepository.count();
    }

}
