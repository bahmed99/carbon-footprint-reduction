package projet.gl.server.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class VehicleTest {
    private Vehicle vehicle;

    @Test
    void testEmptyContructor() {
        // Arrange
        vehicle = new Vehicle();
        // Assert
        assertNotNull(vehicle);
    }

    @Test
    void canGetId() {
        // Arrange
        Long id = 1L;
        vehicle = Vehicle.builder().id(id).build();
        // Act
        Long result = vehicle.getId();
        // Assert
        assertEquals(id, result);
    }

    @Test
    void canSetId() {
        // Arrange
        Long id = 1L;
        vehicle = Vehicle.builder().build();
        // Act
        vehicle.setId(id);
        // Assert
        assertEquals(id, vehicle.getId());
    }

    @Test
    void canGetYearOfCreation() {
        // Arrange
        Integer yearOfCreation = 2021;
        vehicle = Vehicle.builder().yearOfCreation(yearOfCreation).build();
        // Act
        Integer result = vehicle.getYearOfCreation();
        // Assert
        assertEquals(yearOfCreation, result);
    }

    @Test
    void canSetYearOfCreation() {
        // Arrange
        Integer yearOfCreation = 2021;
        vehicle = Vehicle.builder().build();
        // Act
        vehicle.setYearOfCreation(yearOfCreation);
        // Assert
        assertEquals(yearOfCreation, vehicle.getYearOfCreation());
    }

    @Test
    void canGetPriceWithoutConfiguration() {
        // Arrange
        Double priceWithoutConfiguration = 1000.0;
        vehicle = Vehicle.builder().priceWithoutConfiguration(priceWithoutConfiguration).build();
        // Act
        Double result = vehicle.getPriceWithoutConfiguration();
        // Assert
        assertEquals(priceWithoutConfiguration, result);
    }

    @Test
    void canSetPriceWithoutConfiguration() {
        // Arrange
        Double priceWithoutConfiguration = 1000.0;
        vehicle = Vehicle.builder().build();
        // Act
        vehicle.setPriceWithoutConfiguration(priceWithoutConfiguration);
        // Assert
        assertEquals(priceWithoutConfiguration, vehicle.getPriceWithoutConfiguration());
    }

    @Test
    void canGetCreatedAt() {
        // Arrange
        vehicle = Vehicle.builder().createdAt(LocalDate.now()).build();
        // Act
        var result = vehicle.getCreatedAt();
        // Assert
        assertNotNull(result);
    }

    @Test
    void canSetCreatedAt() {
        // Arrange
        var createdAt = LocalDate.now();
        vehicle = Vehicle.builder().build();
        // Act
        vehicle.setCreatedAt(createdAt);
        // Assert
        assertEquals(createdAt, vehicle.getCreatedAt());
    }

    @Test
    void canGetUpdatedAt() {
        // Arrange
        vehicle = Vehicle.builder().updatedAt(LocalDate.now()).build();
        // Act
        var result = vehicle.getUpdatedAt();
        // Assert
        assertNotNull(result);
    }

    @Test
    void canSetUpdatedAt() {
        // Arrange
        var updatedAt = LocalDate.now();
        vehicle = Vehicle.builder().build();
        // Act
        vehicle.setUpdatedAt(updatedAt);
        // Assert
        assertEquals(updatedAt, vehicle.getUpdatedAt());
    }

    @Test
    void canCopy() {
        // Arrange
        Vehicle v = Vehicle.builder().build();
        vehicle = Vehicle.builder().build();
        // Act
        vehicle.copy(v);
        // Assert
        assertEquals(v.getYearOfCreation(), vehicle.getYearOfCreation());
        assertEquals(v.getPriceWithoutConfiguration(), vehicle.getPriceWithoutConfiguration());
        assertEquals(v.getModel(), vehicle.getModel());
        assertEquals(v.getColor(), vehicle.getColor());
        assertEquals(v.getConfigurations(), vehicle.getConfigurations());
    }

    @Test
    void canPrePersist() {
        // Arrange
        vehicle = Vehicle.builder().build();
        // Act
        vehicle.prePersist();
        // Assert
        assertNotNull(vehicle.getCreatedAt());
        assertNotNull(vehicle.getUpdatedAt());
    }

    @Test
    void canPreUpdate() {
        // Arrange
        vehicle = Vehicle.builder().build();
        // Act
        vehicle.preUpdate();
        // Assert
        assertNotNull(vehicle.getUpdatedAt());
    }
}