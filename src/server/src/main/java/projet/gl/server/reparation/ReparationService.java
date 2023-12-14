package projet.gl.server.reparation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Service
public class ReparationService {
    private Random random = new Random();

    private final ReparationRepository reparationRepository;

    @Autowired
    public ReparationService(ReparationRepository reparationRepository) {
        this.reparationRepository = reparationRepository;
    }

    // Méthode pour obtenir tous les reparations
    public List<ReparationDTO> getAllReparationsDTO() {
        List<Reparation> reparations = reparationRepository.findAll();
        return reparations.stream()
                .map(this::convertToReparationDTO)
                .collect(Collectors.toList());
    }

    // convertir de Reparation à ReparationDTO
    private ReparationDTO convertToReparationDTO(Reparation reparation) {
        return new ReparationDTO(reparation);
    }

    // Méthode pour obtenir les reparations par page et par taille
    public Page<ReparationDTO> getReparationsByPageAndSize(Pageable pageable) {
        Page<Reparation> reparations = reparationRepository.findAll(pageable);
        return reparations.map(this::convertToReparationDTO);
    }

    public Reparation getReparationById(Long id) {
        return reparationRepository.findById(id).orElse(null);
    }

    public Reparation createReparation(Reparation reparation) {
        return reparationRepository.save(reparation);
    }

    public Reparation createReparation(long idVehicle, double price) {
        Reparation reparation = new Reparation();
        Long startDate = random.nextLong(30) + 1;
        reparation.setVehicleId(idVehicle);
        reparation.setRepairCost(price * 0.2);
        reparation.setRepairStartDate(LocalDate.now().plusDays(startDate));
        reparation.setRepairEndDate(LocalDate.now().plusDays(random.nextLong(30) + startDate));
        reparation.setRepairCreatedAt(LocalDate.now());
        reparation.setRepairUpdatedAt(LocalDate.now());
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

    // Méthode pour obtenir la taille de la table de réparations
    public long getReparationsTableSize() {
        return reparationRepository.count();
    }
}
