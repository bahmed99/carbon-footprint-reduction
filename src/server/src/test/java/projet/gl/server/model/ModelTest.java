package projet.gl.server.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import projet.gl.server.brand.Brand;

class ModelTest {
    private Model model;

    @Test
    void testEmptyContructor() {
        // Arrange
        model = new Model();
        // Assert
        assertNotNull(model);
    }

    @Test
    void canGetId() {
        // Arrange
        Long id = 1L;
        model = Model.builder().id(id).build();
        // Act
        Long result = model.getId();
        // Assert
        assertEquals(id, result);
    }

    @Test
    void canSetId() {
        // Arrange
        Long id = 1L;
        model = Model.builder().build();
        // Act
        model.setId(id);
        // Assert
        assertEquals(id, model.getId());
    }

    @Test
    void canGetName() {
        // Arrange
        String name = "toyota";
        model = Model.builder().name(name).build();
        // Act
        String result = model.getName();
        // Assert
        assertEquals(name, result);
    }

    @Test
    void canSetName() {
        // Arrange
        String name = "toyota";
        model = Model.builder().build();
        // Act
        model.setName(name);
        // Assert
        assertEquals(name, model.getName());
    }

    @Test
    void canGetCreatedAt() {
        // Arrange
        model = Model.builder().createdAt(LocalDate.now()).build();
        // Act
        var result = model.getCreatedAt();
        // Assert
        assertNotNull(result);
    }

    @Test
    void canSetCreatedAt() {
        // Arrange
        var createdAt = LocalDate.now();
        model = Model.builder().build();
        // Act
        model.setCreatedAt(createdAt);
        // Assert
        assertEquals(createdAt, model.getCreatedAt());
    }

    @Test
    void canGetUpdatedAt() {
        // Arrange
        model = Model.builder().updatedAt(LocalDate.now()).build();
        // Act
        var result = model.getUpdatedAt();
        // Assert
        assertNotNull(result);
    }

    @Test
    void canSetUpdatedAt() {
        // Arrange
        var updatedAt = LocalDate.now();
        model = Model.builder().build();
        // Act
        model.setUpdatedAt(updatedAt);
        // Assert
        assertEquals(updatedAt, model.getUpdatedAt());
    }

    @Test
    void canGetBrandId() {
        // Arrange
        Long brandId = 1L;
        model = Model.builder().brandId(brandId).build();
        // Act
        Long result = model.getBrandId();
        // Assert
        assertEquals(brandId, result);
    }

    @Test
    void canSetBrandId() {
        // Arrange
        Long brandId = 1L;
        model = Model.builder().build();
        // Act
        model.setBrandId(brandId);
        // Assert
        assertEquals(brandId, model.getBrandId());
    }

    @Test
    void canGetBrand() {
        // Arrange
        Brand brand = Brand.builder().build();
        model = Model.builder().brand(brand).build();
        // Act
        Brand result = model.getBrand();
        // Assert
        assertEquals(brand, result);
    }

    @Test
    void canSetBrand() {
        // Arrange
        Brand brand = Brand.builder().build();
        model = Model.builder().build();
        // Act
        model.setBrand(brand);
        // Assert
        assertEquals(brand, model.getBrand());
    }

    @Test
    void canPrePersist() {
        // Arrange
        model = Model.builder().build();
        // Act
        model.prePersist();
        // Assert
        assertNotNull(model.getCreatedAt());
        assertNotNull(model.getUpdatedAt());
    }

    @Test
    void canPreUpdate() {
        // Arrange
        model = Model.builder().build();
        // Act
        model.preUpdate();
        // Assert
        assertNotNull(model.getUpdatedAt());
    }
}
