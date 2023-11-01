package projet.gl.server.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import projet.gl.server.filters.Filters;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> getVehiculeByFilters(Filters filters) {

        return vehicleRepository.findAllByFilters(filters.getBrands(), filters.getModels(), filters.getColors(), filters.getConfigurations()); 
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Optional<Vehicle> vehicleData = vehicleRepository.findById(id);

        if (vehicleData.isPresent()) {
            Vehicle _vehicle = vehicleData.get();
            _vehicle.setYearOfCreation(vehicle.getYearOfCreation());
            _vehicle.setPriceWithoutConfiguration(vehicle.getPriceWithoutConfiguration());
            _vehicle.setCreatedAt(vehicle.getCreatedAt());
            _vehicle.setUpdatedAt(vehicle.getUpdatedAt());
            _vehicle.setModel(vehicle.getModel());
            _vehicle.setColor(vehicle.getColor());
            _vehicle.setConfigurations(vehicle.getConfigurations());
            return vehicleRepository.save(_vehicle);
        } else {
            return null;
        }
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

}
