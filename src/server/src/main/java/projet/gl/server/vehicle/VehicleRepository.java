package projet.gl.server.vehicle;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v " +
            "LEFT JOIN v.color c " +
            "LEFT JOIN v.model m " +
            "LEFT JOIN v.configurations conf " +
            "WHERE (:#{#vehiculeFilterDTO.colorIds} IS NULL OR c.id IN (:#{#vehiculeFilterDTO.colorIds})) " +
            "AND (:#{#vehiculeFilterDTO.modelIds} IS NULL OR m.id IN (:#{#vehiculeFilterDTO.modelIds})) " +
            "AND (:#{#vehiculeFilterDTO.configurationIds} IS NULL OR conf.id IN (:#{#vehiculeFilterDTO.configurationIds})) "
            +
            "AND (:#{#vehiculeFilterDTO.brandIds} IS NULL OR m.brand.id IN (:#{#vehiculeFilterDTO.brandIds}))")
    List<Vehicle> findByFilters(VehiculeFilterDTO vehiculeFilterDTO);
}
