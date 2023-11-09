package projet.gl.server.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ModelDTOTest {
    @Test
    void testConstructorFromModel() {
        // Arrange
        Model model = new Model();
        // Act
        ModelDTO modelDTO = new ModelDTO(model);
        // Assert
        assertNotNull(modelDTO);
    }
}
