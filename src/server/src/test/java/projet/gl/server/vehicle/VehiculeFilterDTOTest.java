package projet.gl.server.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class VehiculeFilterDTOTest {
    private VehiculeFilterDTO vehiculeFilterDTO;

    @Test
    void testEmptyContructor() {
        // Arrange
        vehiculeFilterDTO = new VehiculeFilterDTO();
        // Assert
        assertNotNull(vehiculeFilterDTO);
    }

    @Test
    void canGetColorIds() {
        // Arrange
        List<Long> colorIds = new ArrayList<Long>();
        vehiculeFilterDTO = VehiculeFilterDTO.builder().colorIds(colorIds).build();
        // Act
        List<Long> result = vehiculeFilterDTO.getColorIds();
        // Assert
        assertEquals(colorIds, result);
    }

    @Test
    void canSetColorIds() {
        // Arrange
        List<Long> colorIds = new ArrayList<Long>();
        vehiculeFilterDTO = VehiculeFilterDTO.builder().build();
        // Act
        vehiculeFilterDTO.setColorIds(colorIds);
        // Assert
        assertEquals(colorIds, vehiculeFilterDTO.getColorIds());
    }

    @Test
    void canGetModelIds() {
        // Arrange
        List<Long> modelIds = new ArrayList<Long>();
        vehiculeFilterDTO = VehiculeFilterDTO.builder().modelIds(modelIds).build();
        // Act
        List<Long> result = vehiculeFilterDTO.getModelIds();
        // Assert
        assertEquals(modelIds, result);
    }

    @Test
    void canSetModelIds() {
        // Arrange
        List<Long> modelIds = new ArrayList<Long>();
        vehiculeFilterDTO = VehiculeFilterDTO.builder().build();
        // Act
        vehiculeFilterDTO.setModelIds(modelIds);
        // Assert
        assertEquals(modelIds, vehiculeFilterDTO.getModelIds());
    }

    @Test
    void canGetBrandIds() {
        // Arrange
        List<Long> brandIds = new ArrayList<Long>();
        vehiculeFilterDTO = VehiculeFilterDTO.builder().brandIds(brandIds).build();
        // Act
        List<Long> result = vehiculeFilterDTO.getBrandIds();
        // Assert
        assertEquals(brandIds, result);
    }

    @Test
    void canSetBrandIds() {
        // Arrange
        List<Long> brandIds = new ArrayList<Long>();
        vehiculeFilterDTO = VehiculeFilterDTO.builder().build();
        // Act
        vehiculeFilterDTO.setBrandIds(brandIds);
        // Assert
        assertEquals(brandIds, vehiculeFilterDTO.getBrandIds());
    }

    @Test
    void canGetConfigurationIds() {
        // Arrange
        List<Long> configurationIds = new ArrayList<Long>();
        vehiculeFilterDTO = VehiculeFilterDTO.builder().configurationIds(configurationIds).build();
        // Act
        List<Long> result = vehiculeFilterDTO.getConfigurationIds();
        // Assert
        assertEquals(configurationIds, result);
    }

    @Test
    void canSetConfigurationIds() {
        // Arrange
        List<Long> configurationIds = new ArrayList<Long>();
        vehiculeFilterDTO = VehiculeFilterDTO.builder().build();
        // Act
        vehiculeFilterDTO.setConfigurationIds(configurationIds);
        // Assert
        assertEquals(configurationIds, vehiculeFilterDTO.getConfigurationIds());
    }
}