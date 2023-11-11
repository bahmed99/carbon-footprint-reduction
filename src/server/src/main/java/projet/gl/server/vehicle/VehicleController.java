package projet.gl.server.vehicle;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.stream.Collectors;

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


    @GetMapping("/page/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Vehicle>> getAllVehiclesByPageSize(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok().body(vehicleService.getPaginatedData(pageNumber, pageSize).getContent());
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

            for (int i = 0; i < count; i++) {
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

    @PostMapping("/filters/CountBrand")
    public ResponseEntity<List<Object[]>>findByFiltersByBrand(@RequestBody VehiculeFilterDTO vehiculeFilterDTO) {
        return ResponseEntity.ok().body(vehicleService.findByFiltersByBrand(vehiculeFilterDTO));
    }

    @PostMapping("/filters/color")
    public ResponseEntity<List<Object[]>>findByFiltersByFiltre(@RequestBody VehiculeFilterDTO vehiculeFilterDTO) {
        return ResponseEntity.ok().body(vehicleService.findByFiltersByColor(vehiculeFilterDTO));
    }

    @PostMapping("/filters/configuration")
    public ResponseEntity<List<Object[]>>findByFiltersByConfigurations(@RequestBody VehiculeFilterDTO vehiculeFilterDTO) {
        return ResponseEntity.ok().body(vehicleService.findByFiltersByConfigurations(vehiculeFilterDTO));
    }

    @PostMapping("/filters/model")
    public ResponseEntity<List<Object[]>>findByFiltersByModel(@RequestBody VehiculeFilterDTO vehiculeFilterDTO) {
        return ResponseEntity.ok().body(vehicleService.findByFiltersByModel(vehiculeFilterDTO));
    }

    @PostMapping("/filters/page/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Vehicle>> findByFiltersAndPageSize(@RequestBody VehiculeFilterDTO vehiculeFilterDTO, @PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok().body(vehicleService.findByFiltersAndPageSize(vehiculeFilterDTO, pageNumber, pageSize).getContent());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok().body(vehicleService.countVehicles());
    }


    @GetMapping("/countByModel")
    public ResponseEntity<List<Object[]>> countByModel() {
        return ResponseEntity.ok().body(vehicleService.countByModel());
    }

    @GetMapping("/countByModelName/{brandId}")
    public ResponseEntity<List<Object[]>> countByModelName(@PathVariable int brandId) {
        return ResponseEntity.ok().body(vehicleService.countByModelName(brandId));
    }
    


    @GetMapping("/countByModelName")
    public ResponseEntity<List<BrandModelCountDTO>> countByModelNameForAllBrands() {
        List<BrandModelCountDTO> result = vehicleService.countVehiclesByModelNameForAllBrands();
        return ResponseEntity.ok().body(result);
    }
    

    @GetMapping("/countByBrand")
    public ResponseEntity<List<Object[]>> countByBrand() {
        return ResponseEntity.ok().body(vehicleService.countByBrand());
    }

    @GetMapping("/countByBrandName")
    public ResponseEntity<List<Object[]>> countByBrandName() {
        return ResponseEntity.ok().body(vehicleService.countByBrandName());
    }

    @GetMapping("/countByColor")
    public ResponseEntity<List<Object[]>> countByColor() {
        return ResponseEntity.ok().body(vehicleService.countByColor());
    }
    
    @PostMapping("/countByFilter")
    public ResponseEntity<Long> countByFilter(@RequestBody VehiculeFilterDTO vehiculeFilterDTO) {
        return ResponseEntity.ok().body(vehicleService.countByFilter(vehiculeFilterDTO));
    }

}
