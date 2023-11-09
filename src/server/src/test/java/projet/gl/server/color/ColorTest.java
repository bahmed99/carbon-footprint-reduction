package projet.gl.server.color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ColorTest {
    private Color color;

    @Test
    void testEmptyContructor() {
        // Arrange
        color = new Color();
        // Assert
        assertNotNull(color);
    }

    @Test
    void canGetId() {
        // Arrange
        Long id = 1L;
        color = Color.builder().id(id).build();
        // Act
        Long result = color.getId();
        // Assert
        assertEquals(id, result);
    }

    @Test
    void canSetId() {
        // Arrange
        Long id = 1L;
        color = Color.builder().build();
        // Act
        color.setId(id);
        // Assert
        assertEquals(id, color.getId());
    }

    @Test
    void canGetName() {
        // Arrange
        String name = "red";
        color = Color.builder().name(name).build();
        // Act
        String result = color.getName();
        // Assert
        assertEquals(name, result);
    }

    @Test
    void canSetName() {
        // Arrange
        String name = "red";
        color = Color.builder().build();
        // Act
        color.setName(name);
        // Assert
        assertEquals(name, color.getName());
    }
}
