package projet.gl.server.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public Sale createSale(long idVehicle, double price) {
        Random random = new Random();
        Sale sale = new Sale();
        sale.setVehicleId(idVehicle);
        sale.setPrice(price * 1.2);
        sale.setDateDilevery(LocalDate.now().plusDays(random.nextInt(30) + 1));
        sale.setDateExpiratonInsurance(LocalDate.now().plusYears(random.nextInt(4) + 1));
        return saleRepository.save(sale);
    }
}
