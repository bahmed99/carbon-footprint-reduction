package projet.gl.server.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import projet.gl.server.reparation.Reparation;

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

    //Méthode pour obtenir les rentals par page et par taille
    public Page<Rental> getRentalsByPageAndSize(Pageable pageable) {
        return rentalRepository.findAll(pageable);
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
        rental.setRentalFee(rentalFee * 0.4);
        rental.setRentalEndDate(LocalDate.now().plusDays(random.nextInt(30) + 1));
        rental.setInsuranceExpirationDate(LocalDate.now().plusYears(random.nextInt(4) + 1));
        return rentalRepository.save(rental);
    }

     //Méthode pour obtenir la taille de la table de rentals
        public long getRentalsTableSize() {
            return rentalRepository.count();
        }

}
