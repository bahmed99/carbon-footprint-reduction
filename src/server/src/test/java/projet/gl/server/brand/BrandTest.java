package projet.gl.server.brand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class BrandTest {
    private Brand brand;

    @Test
    void testEmptyContructor() {
        // Arrange
        brand = new Brand();
        // Assert
        assertNotNull(brand);
    }

    @Test
    void canGetId() {
        // Arrange
        Long id = 1L;
        brand = Brand.builder().id(id).build();
        // Act
        Long result = brand.getId();
        // Assert
        assertEquals(id, result);
    }

    @Test
    void canSetId() {
        // Arrange
        Long id = 1L;
        brand = Brand.builder().build();
        // Act
        brand.setId(id);
        // Assert
        assertEquals(id, brand.getId());
    }

    @Test
    void canGetName() {
        // Arrange
        String name = "toyota";
        brand = Brand.builder().name(name).build();
        // Act
        String result = brand.getName();
        // Assert
        assertEquals(name, result);
    }

    @Test
    void canSetName() {
        // Arrange
        String name = "toyota";
        brand = Brand.builder().build();
        // Act
        brand.setName(name);
        // Assert
        assertEquals(name, brand.getName());
    }

    @Test
    void canGetCreatedAt() {
        // Arrange
        brand = Brand.builder().createdAt(LocalDate.now()).build();
        // Act
        var result = brand.getCreatedAt();
        // Assert
        assertNotNull(result);
    }

    @Test
    void canSetCreatedAt() {
        // Arrange
        var createdAt = LocalDate.now();
        brand = Brand.builder().build();
        // Act
        brand.setCreatedAt(createdAt);
        // Assert
        assertEquals(createdAt, brand.getCreatedAt());
    }

    @Test
    void canGetUpdatedAt() {
        // Arrange
        brand = Brand.builder().updatedAt(LocalDate.now()).build();
        // Act
        var result = brand.getUpdatedAt();
        // Assert
        assertNotNull(result);
    }

    @Test
    void canSetUpdatedAt() {
        // Arrange
        var updatedAt = LocalDate.now();
        brand = Brand.builder().build();
        // Act
        brand.setUpdatedAt(updatedAt);
        // Assert
        assertEquals(updatedAt, brand.getUpdatedAt());
    }

    @Test
    void canPrePersist() {
        // Arrange
        brand = Brand.builder().build();
        // Act
        brand.prePersist();
        // Assert
        assertNotNull(brand.getCreatedAt());
        assertNotNull(brand.getUpdatedAt());
    }

    @Test
    void canPreUpdate() {
        // Arrange
        brand = Brand.builder().build();
        // Act
        brand.preUpdate();
        // Assert
        assertNotNull(brand.getUpdatedAt());
    }
}
