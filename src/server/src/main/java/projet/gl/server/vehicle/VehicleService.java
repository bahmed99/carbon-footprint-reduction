package projet.gl.server.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.gl.server.color.ColorRepository;
import java.util.List;
import java.util.Optional;


@Service
public class VehicleService {
    
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService (VehicleRepository vehicleRepository)
    {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicules() {
        return vehicleRepository.findAll();
    }

    public Optional< Vehicle> getVehiculeById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle createVehicule(Vehicle vehicle)
    {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicule(Long id , Vehicle vehicle)
    {
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

    public void deleteVehicule(Long id)
    {
        vehicleRepository.deleteById(id);
    }

}
