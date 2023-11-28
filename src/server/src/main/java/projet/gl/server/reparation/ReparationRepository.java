package projet.gl.server.reparation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparationRepository extends JpaRepository<Reparation, Long> {
}
