package projet.gl.server.reparation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Random;
import java.time.LocalDate;

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

    //Méthode pour obtenir les reparations par page et par taille
    public Page<Reparation> getReparationsByPageAndSize(Pageable pageable) {
        return reparationRepository.findAll(pageable);
    }

    public Reparation getReparationById(Long id) {
        return reparationRepository.findById(id).orElse(null);
    }

    public Reparation createReparation(Reparation reparation) {
        return reparationRepository.save(reparation);
    }

    public Reparation createReparation(long idVehicle, double price) {
        Random random = new Random();
        Reparation reparation = new Reparation();
        reparation.setVehicleId(idVehicle);
        reparation.setRepairCost(price * 0.2);
        reparation.setRepairStartDate(LocalDate.now().plusDays(random.nextInt(30) + 1));
        reparation.setRepairEndDate(LocalDate.now().plusDays(random.nextInt(30) + 1));
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

     //Méthode pour obtenir la taille de la table de réparations
     public long getReparationsTableSize() {
        return reparationRepository.count();
    }
}
