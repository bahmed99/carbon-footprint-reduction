package projet.gl.server.vehicle;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle")
    List<Vehicle> findAllByFilters(  List<Integer> Brands, List<Integer> Models, List<Integer> Colors, List<Integer> Configurations); 
}
