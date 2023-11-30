package projet.gl.server.reparation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reparations")
public class ReparationController {

    private final ReparationService reparationService;

    @Autowired
    public ReparationController(ReparationService reparationService) {
        this.reparationService = reparationService;
    }

    @GetMapping
    public ResponseEntity<List<Reparation>> getAllReparations() {
        List<Reparation> reparations = reparationService.getAllReparations();
        return new ResponseEntity<>(reparations, HttpStatus.OK);
    }

    //Méthode pour prendre en charge la pagination avec le chemin /page/size
    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<List<Reparation>> getReparationsByPageAndSize(
            @PathVariable int page,
            @PathVariable int size) {
        try {
            Page<Reparation> reparations = reparationService.getReparationsByPageAndSize(PageRequest.of(page, size));
            return new ResponseEntity<>(reparations.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Méthode pour obtenir la taille de la table de réparations
    @GetMapping("/size")
    public ResponseEntity<Long> getReparationsTableSize() {
        try {
            long tableSize = reparationService.getReparationsTableSize();
            return new ResponseEntity<>(tableSize, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reparation> getReparationById(@PathVariable Long id) {
        Reparation reparation = reparationService.getReparationById(id);
        return new ResponseEntity<>(reparation, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reparation> createReparation(@RequestBody Reparation reparation) {
        Reparation createdReparation = reparationService.createReparation(reparation);
        return new ResponseEntity<>(createdReparation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reparation> updateReparation(@PathVariable Long id, @RequestBody Reparation reparation) {
        Reparation updatedReparation = reparationService.updateReparation(id, reparation);
        return new ResponseEntity<>(updatedReparation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReparation(@PathVariable Long id) {
        reparationService.deleteReparation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
