package projet.gl.server.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public Rental createRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Rental createRental(long idVehicle, double rentalFee) {
        Random random = new Random();
        Rental rental = new Rental();
        rental.setVehicleId(idVehicle);
        rental.setRentalFee(rentalFee * 1.2);
        rental.setRentalEndDate(LocalDate.now().plusDays(random.nextInt(30) + 1));
        rental.setInsuranceExpirationDate(LocalDate.now().plusYears(random.nextInt(4) + 1));
        return rentalRepository.save(rental);
    }
}
