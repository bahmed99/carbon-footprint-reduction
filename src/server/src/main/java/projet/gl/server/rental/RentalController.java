package projet.gl.server.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id)
                .map(rental -> new ResponseEntity<>(rental, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<RentalDTO> createRental(@RequestBody RentalDTO rentalDTO) {
        Rental rental = convertToRental(rentalDTO);
        Rental createdRental = rentalService.createRental(rental);
        return new ResponseEntity<>(convertToRentalDTO(createdRental), HttpStatus.CREATED);
    }

    private RentalDTO convertToRentalDTO(Rental rental) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rental.getId());
        rentalDTO.setRentalFee(rental.getRentalFee());
        rentalDTO.setRentalStartDate(rental.getRentalStartDate());
        rentalDTO.setRentalEndDate(rental.getRentalEndDate());
        rentalDTO.setInsuranceExpirationDate(rental.getInsuranceExpirationDate());
        rentalDTO.setRentalCreatedAt(rental.getRentalCreatedAt());
        rentalDTO.setRentalUpdatedAt(rental.getRentalUpdatedAt());
        rentalDTO.setVehicleId(rental.getVehicleId());
        return rentalDTO;
    }

    private Rental convertToRental(RentalDTO rentalDTO) {
        Rental rental = new Rental();
        rental.setId(rentalDTO.getId());
        rental.setRentalFee(rentalDTO.getRentalFee());
        rental.setRentalEndDate(rentalDTO.getRentalEndDate());
        rental.setInsuranceExpirationDate(rentalDTO.getInsuranceExpirationDate());
        rental.setVehicleId(rentalDTO.getVehicleId());
        return rental;
    }
}
