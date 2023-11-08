package projet.gl.server.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {
    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private Optional<Vehicle> vehicleData;

    private AutoCloseable autoCloseable;

    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        autoCloseable = org.mockito.MockitoAnnotations.openMocks(this);
        vehicleService = new VehicleService(vehicleRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllVehicles() {
        // Act
        vehicleService.getAllVehicles();
        // Assert
        verify(vehicleRepository).findAll();
    }

    @Test
    void canGetVehicleById() {
        // Arrange
        Long id = 1L;
        // Act
        vehicleService.getVehicleById(id);
        // Assert
        verify(vehicleRepository).findById(id);
    }

    @Test
    void canCreateVehicle() {
        // Arrange
        Vehicle vehicle = Vehicle.builder().yearOfCreation(2021).priceWithoutConfiguration(10000).build();
        // Act
        vehicleService.createVehicle(vehicle);
        // Assert
        verify(vehicleRepository).save(vehicle);
    }

    @Test
    void canUpdateVehicle() {
        // Arrange
        Long id = 1L;
        Vehicle vehicle = Vehicle.builder().id(id).yearOfCreation(2021).priceWithoutConfiguration(10000).build();
        when(vehicleRepository.findById(id)).thenReturn(vehicleData);
        when(vehicleData.isPresent()).thenReturn(true);
        when(vehicleData.get()).thenReturn(vehicle);
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        // Act
        Vehicle testVehicle = vehicleService.createVehicle(vehicle);
        vehicle.setYearOfCreation(2022);
        testVehicle = vehicleService.updateVehicle(id, vehicle);
        // Assert
        verify(vehicleData).isPresent();

        assertEquals(id, testVehicle.getId());
        assertEquals(2022, testVehicle.getYearOfCreation());
    }

    @Test
    void canNotUpdateVehicle() {
        // Arrange
        Long id = 1L;
        Vehicle vehicle = Vehicle.builder().id(id).yearOfCreation(2021).build();
        // Act
        vehicle = vehicleService.updateVehicle(id, vehicle);
        // Assert
        assertNull(vehicle);
    }

    @Test
    void canDeleteVehicle() {
        // Arrange
        Long id = 1L;
        // Act
        vehicleService.deleteVehicle(id);
        // Assert
        verify(vehicleRepository).deleteById(id);
    }

    @Test
    void canFindByFilters() {
        // Arrange
        VehiculeFilterDTO vehiculeFilterDTO = new VehiculeFilterDTO();
        // Act
        vehicleService.findByFilters(vehiculeFilterDTO);
        // Assert
        verify(vehicleRepository).findByFilters(vehiculeFilterDTO);
    }

    @Test
    void canFindByFiltersByBrand() {
        // Arrange
        VehiculeFilterDTO vehiculeFilterDTO = new VehiculeFilterDTO();
        // Act
        vehicleService.findByFiltersByBrand(vehiculeFilterDTO);
        // Assert
        verify(vehicleRepository).findByFiltersByBrand(vehiculeFilterDTO);
    }

    @Test
    void canFindByFiltersByColor() {
        // Arrange
        VehiculeFilterDTO vehiculeFilterDTO = new VehiculeFilterDTO();
        // Act
        vehicleService.findByFiltersByColor(vehiculeFilterDTO);
        // Assert
        verify(vehicleRepository).findByFiltersByColor(vehiculeFilterDTO);
    }

    @Test
    void canFindByFiltersByConfigurations() {
        // Arrange
        VehiculeFilterDTO vehiculeFilterDTO = new VehiculeFilterDTO();
        // Act
        vehicleService.findByFiltersByConfigurations(vehiculeFilterDTO);
        // Assert
        verify(vehicleRepository).findByFiltersByConfigurations(vehiculeFilterDTO);
    }

    @Test
    void canFindByFiltersByModel() {
        // Arrange
        VehiculeFilterDTO vehiculeFilterDTO = new VehiculeFilterDTO();
        // Act
        vehicleService.findByFiltersByModel(vehiculeFilterDTO);
        // Assert
        verify(vehicleRepository).findByFiltersByModel(vehiculeFilterDTO);
    }

    @Test
    void canFindByFiltersAndPageSize() {
        // Arrange
        VehiculeFilterDTO vehiculeFilterDTO = new VehiculeFilterDTO();
        int pageNumber = 0;
        int pageSize = 10;
        // Act
        vehicleService.findByFiltersAndPageSize(vehiculeFilterDTO, pageNumber, pageSize);
        // Assert
        verify(vehicleRepository).findByFiltersAndPageSize(eq(vehiculeFilterDTO), Mockito.any());
    }

    @Test
    void canGetPaginatedData() {
        // Arrange
        int pageNumber = 0;
        int pageSize = 10;
        // Act
        vehicleService.getPaginatedData(pageNumber, pageSize);
        // Assert
        verify(vehicleRepository).findAll((Pageable) Mockito.any());
    }

    @Test
    void canCountVehicles() {
        // Act
        vehicleService.countVehicles();
        // Assert
        verify(vehicleRepository).count();
    }

    @Test
    void canCountByFilter() {
        // Arrange
        VehiculeFilterDTO vehiculeFilterDTO = new VehiculeFilterDTO();
        // Act
        vehicleService.countByFilter(vehiculeFilterDTO);
        // Assert
        verify(vehicleRepository).countByFilter(vehiculeFilterDTO);
    }

    @Test
    void canCountByModel() {
        // Act
        vehicleService.countByModel();
        // Assert
        verify(vehicleRepository).countVehiclesByModel();
    }

    @Test
    void canCountByBrand() {
        // Act
        vehicleService.countByBrand();
        // Assert
        verify(vehicleRepository).countVehiclesByBrand();
    }
}
