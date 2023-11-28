package projet.gl.server.reparation;

import org.springframework.beans.factory.annotation.Autowired;
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
