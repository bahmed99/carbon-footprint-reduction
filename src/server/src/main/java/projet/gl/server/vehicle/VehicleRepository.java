package projet.gl.server.vehicle;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
        @Query("SELECT DISTINCT v FROM Vehicle v " +
                        "LEFT JOIN v.color c " +
                        "LEFT JOIN v.model m " +
                        "LEFT JOIN v.configurations conf " +
                        "WHERE (:#{#vehiculeFilterDTO.colorIds} IS NULL OR c.id IN (:#{#vehiculeFilterDTO.colorIds})) "
                        +
                        "AND (:#{#vehiculeFilterDTO.modelIds} IS NULL OR m.id IN (:#{#vehiculeFilterDTO.modelIds})) " +
                        "AND (:#{#vehiculeFilterDTO.configurationIds} IS NULL OR conf.id IN (:#{#vehiculeFilterDTO.configurationIds})) "
                        +
                        "AND (:#{#vehiculeFilterDTO.brandIds} IS NULL OR m.brand.id IN (:#{#vehiculeFilterDTO.brandIds}))")
        List<Vehicle> findByFilters(VehiculeFilterDTO vehiculeFilterDTO);

        @Query("SELECT DISTINCT v FROM Vehicle v " +
                        "LEFT JOIN v.color c " +
                        "LEFT JOIN v.model m " +
                        "LEFT JOIN v.configurations conf " +
                        "WHERE (:#{#vehiculeFilterDTO.colorIds} IS NULL OR c.id IN (:#{#vehiculeFilterDTO.colorIds})) "
                        +
                        "AND (:#{#vehiculeFilterDTO.modelIds} IS NULL OR m.id IN (:#{#vehiculeFilterDTO.modelIds})) " +
                        "AND (:#{#vehiculeFilterDTO.configurationIds} IS NULL OR conf.id IN (:#{#vehiculeFilterDTO.configurationIds})) "
                        +
                        "AND (:#{#vehiculeFilterDTO.brandIds} IS NULL OR m.brand.id IN (:#{#vehiculeFilterDTO.brandIds}))")
        Page<Vehicle> findByFiltersAndPageSize(VehiculeFilterDTO vehiculeFilterDTO, Pageable pageable);

        @Query("SELECT v.model.id AS modelId, COUNT(DISTINCT v.id) AS vehicleCount FROM Vehicle v GROUP BY v.model.id")
        List<Object[]> countVehiclesByModel();



        @Query("SELECT m.name AS modelName, COUNT(DISTINCT v.id) FROM Vehicle v " +
        "JOIN v.model m " +
        "WHERE m.brand.id = :brandId GROUP BY m.name")
        List<Object[]> countVehiclesByModelName(@Param("brandId") int brandId);

            
        @Query("SELECT m.brand.id AS brandId, COUNT(DISTINCT v.id) AS vehicleCount FROM Vehicle v " +
                        "LEFT JOIN v.model m " +
                        "GROUP BY m.brand.id")
        List<Object[]> countVehiclesByBrand();


        @Query("SELECT n.brand.name AS brandName, COUNT(DISTINCT v.id) AS vehicleCount FROM Vehicle v " +
                        "LEFT JOIN v.model n " +
                        "GROUP BY n.brand.name")
        List<Object[]> countVehiclesByBrandName();


        @Query("SELECT c.name AS colorName, COUNT(v.id) AS vehicleCount FROM Vehicle v " +
        "JOIN v.color c " +
        "GROUP BY c.id")
        List<Object[]> countVehiclesByColor();
 
        // findByFiltersByBrand

        @Query("SELECT m.brand.id AS brandId, COUNT(DISTINCT v.id) AS vehicleCount FROM Vehicle v " +
                        "LEFT JOIN v.color c " +
                        "LEFT JOIN v.model m " +
                        "LEFT JOIN v.configurations conf " +
                        "WHERE (:#{#vehicleFilterDTO.colorIds} IS NULL OR c.id IN (:#{#vehicleFilterDTO.colorIds})) " +
                        "AND (:#{#vehicleFilterDTO.modelIds} IS NULL OR m.id IN (:#{#vehicleFilterDTO.modelIds})) " +
                        "AND (:#{#vehicleFilterDTO.configurationIds} IS NULL OR conf.id IN (:#{#vehicleFilterDTO.configurationIds})) "
                        +
                        "AND (:#{#vehicleFilterDTO.brandIds} IS NULL OR m.brand.id IN (:#{#vehicleFilterDTO.brandIds}))"
                        +
                        "GROUP BY m.brand.id")
        List<Object[]> findByFiltersByBrand(VehiculeFilterDTO vehicleFilterDTO);

        @Query("SELECT c.id AS ColorId, COUNT(DISTINCT v.id) AS vehicleCount FROM Vehicle v " +
                        "LEFT JOIN v.color c " +
                        "LEFT JOIN v.model m " +
                        "LEFT JOIN v.configurations conf " +
                        "WHERE (:#{#vehicleFilterDTO.colorIds} IS NULL OR c.id IN (:#{#vehicleFilterDTO.colorIds})) " +
                        "AND (:#{#vehicleFilterDTO.modelIds} IS NULL OR m.id IN (:#{#vehicleFilterDTO.modelIds})) " +
                        "AND (:#{#vehicleFilterDTO.configurationIds} IS NULL OR conf.id IN (:#{#vehicleFilterDTO.configurationIds})) "
                        +
                        "AND (:#{#vehicleFilterDTO.brandIds} IS NULL OR m.brand.id IN (:#{#vehicleFilterDTO.brandIds}))"
                        +
                        "GROUP BY c.id")
        List<Object[]> findByFiltersByColor(VehiculeFilterDTO vehicleFilterDTO);

        @Query("SELECT conf.id AS ConfigurationId, COUNT(DISTINCT v.id) AS vehicleCount FROM Vehicle v " +
                        "LEFT JOIN v.color c " +
                        "LEFT JOIN v.model m " +
                        "LEFT JOIN v.configurations conf " +
                        "WHERE (:#{#vehicleFilterDTO.colorIds} IS NULL OR c.id IN (:#{#vehicleFilterDTO.colorIds})) " +
                        "AND (:#{#vehicleFilterDTO.modelIds} IS NULL OR m.id IN (:#{#vehicleFilterDTO.modelIds})) " +
                        "AND (:#{#vehicleFilterDTO.configurationIds} IS NULL OR conf.id IN (:#{#vehicleFilterDTO.configurationIds})) "
                        +
                        "AND (:#{#vehicleFilterDTO.brandIds} IS NULL OR m.brand.id IN (:#{#vehicleFilterDTO.brandIds}))"
                        +

                        "GROUP BY conf.id")
        List<Object[]> findByFiltersByConfigurations(VehiculeFilterDTO vehicleFilterDTO);

        @Query("SELECT m.id AS ModelId, COUNT(DISTINCT v.id) AS vehicleCount FROM Vehicle v " +
                        "LEFT JOIN v.color c " +
                        "LEFT JOIN v.model m " +
                        "LEFT JOIN v.configurations conf " +
                        "WHERE (:#{#vehicleFilterDTO.colorIds} IS NULL OR c.id IN (:#{#vehicleFilterDTO.colorIds})) " +
                        "AND (:#{#vehicleFilterDTO.modelIds} IS NULL OR m.id IN (:#{#vehicleFilterDTO.modelIds})) " +
                        "AND (:#{#vehicleFilterDTO.configurationIds} IS NULL OR conf.id IN (:#{#vehicleFilterDTO.configurationIds})) "
                        +
                        "AND (:#{#vehicleFilterDTO.brandIds} IS NULL OR m.brand.id IN (:#{#vehicleFilterDTO.brandIds}))"
                        +
                        "GROUP BY m.id")
        List<Object[]> findByFiltersByModel(VehiculeFilterDTO vehicleFilterDTO);

        @Query("SELECT COUNT(DISTINCT v.id) FROM Vehicle v " +
                        "LEFT JOIN v.color c " +
                        "LEFT JOIN v.model m " +
                        "LEFT JOIN v.configurations conf " +
                        "WHERE (:#{#vehicleFilterDTO.colorIds} IS NULL OR c.id IN (:#{#vehicleFilterDTO.colorIds})) " +
                        "AND (:#{#vehicleFilterDTO.modelIds} IS NULL OR m.id IN (:#{#vehicleFilterDTO.modelIds})) " +
                        "AND (:#{#vehicleFilterDTO.configurationIds} IS NULL OR conf.id IN (:#{#vehicleFilterDTO.configurationIds})) "
                        +
                        "AND (:#{#vehicleFilterDTO.brandIds} IS NULL OR m.brand.id IN (:#{#vehicleFilterDTO.brandIds}))")
        long countByFilter(VehiculeFilterDTO vehicleFilterDTO);

}
