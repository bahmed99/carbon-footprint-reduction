package projet.gl.server.rental;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByVehicleId(Long vehicleId);

    void deleteByVehicleId(Long vehicleId);
}
