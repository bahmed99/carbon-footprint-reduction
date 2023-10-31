package projet.gl.server.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query("SELECT m FROM Model m JOIN m.brand b WHERE b.id = :brandId")
    List<Model> findModelsByBrand(Long brandId);
    
}
