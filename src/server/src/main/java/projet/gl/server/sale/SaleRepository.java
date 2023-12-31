package projet.gl.server.sale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    // @Query("SELECT s FROM sale s JOIN s.vehicle v WHERE v.id = :vehicleId")
    // List<SaleDTO> findSalesByVehicle(Long vehicleId);

}
