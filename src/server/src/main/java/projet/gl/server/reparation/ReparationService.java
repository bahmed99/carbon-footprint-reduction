package projet.gl.server.reparation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReparationService {

    private final ReparationRepository reparationRepository;

    @Autowired
    public ReparationService(ReparationRepository reparationRepository) {
        this.reparationRepository = reparationRepository;
    }

    public List<Reparation> getAllReparations() {
        return reparationRepository.findAll();
    }

    public Reparation getReparationById(Long id) {
        return reparationRepository.findById(id).orElse(null);
    }

    public Reparation createReparation(Reparation reparation) {
        return reparationRepository.save(reparation);
    }

    public Reparation updateReparation(Long id, Reparation reparation) {
        Reparation existingReparation = reparationRepository.findById(id).orElse(null);
        if (existingReparation != null) {
            existingReparation.setRepairCost(reparation.getRepairCost());
            existingReparation.setRepairStartDate(reparation.getRepairStartDate());
            existingReparation.setRepairEndDate(reparation.getRepairEndDate());
            existingReparation.setRepairCreatedAt(reparation.getRepairCreatedAt());
            existingReparation.setRepairUpdatedAt(reparation.getRepairUpdatedAt());
            existingReparation.setVehicleId(reparation.getVehicleId());
            existingReparation.setRepairedVehicle(reparation.getRepairedVehicle());
            return reparationRepository.save(existingReparation);
        }
        return null;
    }

    public void deleteReparation(Long id) {
        reparationRepository.deleteById(id);
    }
}
