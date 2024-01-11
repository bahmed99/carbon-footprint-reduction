package projet.gl.server.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import projet.gl.server.rental.RentalRepository;
import projet.gl.server.reparation.ReparationRepository;
import projet.gl.server.vehicle.Vehicle;
import projet.gl.server.vehicle.VehicleRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SaleService {
    private SaleRepository saleRepository;
    private RentalRepository rentalRepository;
    private ReparationRepository reparationRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    public SaleService(SaleRepository saleRepository, RentalRepository rentalRepository,
            ReparationRepository reparationRepository) {
        this.saleRepository = saleRepository;
        this.rentalRepository = rentalRepository;
        this.reparationRepository = reparationRepository;
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

    @Transactional
    public Sale createSale(long idVehicle,double price, String initialState) {
        Random random = new Random();
        Sale sale = new Sale();

        if (initialState.equals("RENTAL")) {
            rentalRepository.deleteByVehicleId(idVehicle);
        } else if (initialState.equals("REPARATION")) {
            reparationRepository.deleteByVehicleId(idVehicle);
        }

        sale.setVehicleId(idVehicle);
        Optional<Vehicle> vehicle = vehicleRepository.findById(idVehicle);
        sale.setVehicle(vehicle.get());
        sale.setPrice(vehicle.get().getPriceWithoutConfiguration() * 1.2);
        sale.setDateDelivery(LocalDate.now().plusDays(random.nextInt(30) + 1));
        sale.setDateExpiratonInsurance(LocalDate.now().plusYears(random.nextInt(4) + 1));

        return saleRepository.save(sale);
    }

    // Méthode pour obtenir la taille de la table de sales
    public long getSalesTableSize() {
        return saleRepository.count();
    }

}
