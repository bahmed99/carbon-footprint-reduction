package projet.gl.server.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ConfigurationTest {
    private Configuration configuration;

    @Test
    void testEmptyContructor() {
        // Arrange
        configuration = new Configuration();
        // Assert
        assertNotNull(configuration);
    }

    @Test
    void canGetId() {
        // Arrange
        Long id = 1L;
        configuration = Configuration.builder().id(id).build();
        // Act
        Long result = configuration.getId();
        // Assert
        assertEquals(id, result);
    }

    @Test
    void canSetId() {
        // Arrange
        Long id = 1L;
        configuration = Configuration.builder().build();
        // Act
        configuration.setId(id);
        // Assert
        assertEquals(id, configuration.getId());
    }

    @Test
    void canGetName() {
        // Arrange
        String name = "red";
        configuration = Configuration.builder().name(name).build();
        // Act
        String result = configuration.getName();
        // Assert
        assertEquals(name, result);
    }

    @Test
    void canSetName() {
        // Arrange
        String name = "red";
        configuration = Configuration.builder().build();
        // Act
        configuration.setName(name);
        // Assert
        assertEquals(name, configuration.getName());
    }
}
