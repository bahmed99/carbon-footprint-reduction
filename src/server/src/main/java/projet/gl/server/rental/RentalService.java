package projet.gl.server.rental;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import projet.gl.server.reparation.ReparationRepository;
import projet.gl.server.sale.SaleRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private RentalRepository rentalRepository;
    private ReparationRepository reparationRepository;
    private SaleRepository saleRepository;

    public RentalService(RentalRepository rentalRepository, ReparationRepository reparationRepository,
            SaleRepository saleRepository) {
        this.rentalRepository = rentalRepository;
        this.reparationRepository = reparationRepository;
        this.saleRepository = saleRepository;
    }

    // Méthode pour obtenir tous les rentals
    public List<RentalDTO> getAllRentalsDTO() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(this::convertToRentalDTO)
                .collect(Collectors.toList());
    }

    // convertir de Rental à RentalDTO
    private RentalDTO convertToRentalDTO(Rental rental) {
        return new RentalDTO(rental);
    }

    // Méthode pour obtenir les rentals par page et par taille
    public Page<RentalDTO> getRentalsByPageAndSize(Pageable pageable) {
        Page<Rental> rentals = rentalRepository.findAll(pageable);
        return rentals.map(this::convertToRentalDTO);
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public Rental createRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Rental createRental(long idVehicle, double rentalFee, String initialState) {
        Random random = new Random();
        Rental rental = new Rental();

        if (initialState.equals("REPERATION")) {
            reparationRepository.deleteByVehicleId(idVehicle);
        } else if (initialState.equals("SALE")) {
            saleRepository.deleteByVehicleId(idVehicle);
        }

        rental.setVehicleId(idVehicle);
        rental.setRentalFee(rentalFee * 0.4);
        rental.setRentalEndDate(LocalDate.now().plusDays(random.nextInt(30) + 1));
        rental.setInsuranceExpirationDate(LocalDate.now().plusYears(random.nextInt(4) + 1));
        return rentalRepository.save(rental);
    }

    // Méthode pour obtenir la taille de la table de rentals
    public long getRentalsTableSize() {
        return rentalRepository.count();
    }

}
