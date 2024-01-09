package projet.gl.server.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<RentalDTO>> getAllRentals() {
        List<RentalDTO> rentals = rentalService.getAllRentalsDTO();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    // Méthode pour prendre en charge la pagination avec le chemin /page/size
    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<List<RentalDTO>> getRentalsByPageAndSize(
            @PathVariable int page,
            @PathVariable int size) {
        try {
            Page<RentalDTO> rentals = rentalService.getRentalsByPageAndSize(PageRequest.of(page, size));
            return new ResponseEntity<>(rentals.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Méthode pour obtenir la taille de la table de rentals
    @GetMapping("/size")
    public ResponseEntity<Long> getRentalsTableSize() {
        try {
            long tableSize = rentalService.getRentalsTableSize();
            return new ResponseEntity<>(tableSize, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @PostMapping("/{vehicleId}/{rentalFee}/{initialState}")
    public ResponseEntity<RentalDTO> changeToRental(@PathVariable Long vehicleId, @PathVariable double rentalFee, @PathVariable String initialState) {
        Rental createdRental = rentalService.createRental(vehicleId, rentalFee, initialState);
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
        rentalDTO.setModelName(rental.getModelName());
        rentalDTO.setBrandName(rental.getBrandName());
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
