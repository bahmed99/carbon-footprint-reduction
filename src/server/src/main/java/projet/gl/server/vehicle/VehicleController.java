package projet.gl.server.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok().body(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{count}")
    public ResponseEntity<Void> createVehicle(@PathVariable int count, @RequestBody Vehicle vehicle) {
        
        if (count == 0) {
            vehicleService.createVehicle(vehicle);
        } else {
            Vehicle newVehicle;
            
            for(int i=0;i < count;i++){
                newVehicle = new Vehicle();
                newVehicle.Copy(vehicle);
                vehicleService.createVehicle(newVehicle);
            }
        }

        return ResponseEntity.ok().build();       
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/filters")
    public ResponseEntity<List<Vehicle>> findByFilters(@RequestBody VehiculeFilterDTO vehiculeFilterDTO) {
        return ResponseEntity.ok().body(vehicleService.findByFilters(vehiculeFilterDTO));
    }
}
