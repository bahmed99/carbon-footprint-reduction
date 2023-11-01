package projet.gl.server.vehicle;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v JOIN v.color c JOIN v.model m JOIN v.configurations conf WHERE c.id IN (:#{#vehiculeFilterDTO.colorIds}) AND m.id IN (:#{#vehiculeFilterDTO.modelIds}) AND conf.id IN (:#{#vehiculeFilterDTO.configurationIds})")
    List<Vehicle> findByFilters(VehiculeFilterDTO vehiculeFilterDTO);
}
