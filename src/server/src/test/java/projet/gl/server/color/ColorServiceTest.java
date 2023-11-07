package projet.gl.server.color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ColorServiceTest {
    @Mock
    private ColorRepository colorRepository;

    private AutoCloseable autoCloseable;

    private ColorService colorService;

    @BeforeEach
    void setUp() {
        autoCloseable = org.mockito.MockitoAnnotations.openMocks(this);
        colorService = new ColorService(colorRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllColors() {
        // Act
        colorService.getAllColors();
        // Assert
        verify(colorRepository).findAll();
    }

    @Test
    void canGetColorById() {
        // Arrange
        Long id = 1L;
        // Act
        colorService.getColorById(id);
        // Assert
        verify(colorRepository).findById(id);
    }

    @Test
    void canCreateColor() {
        // Arrange
        Color color = Color.builder().name("red").build();
        // Act
        colorService.createColor(color);
        // Assert
        verify(colorRepository).save(color);
    }

    @Test
    void canUpdateColor() {
        // Arrange
        Long id = 1L;
        Color color = Color.builder().id(id).name("red").build();
        when(colorRepository.existsById(id)).thenReturn(true);
        when(colorRepository.save(color)).thenReturn(color);
        // Act
        Color testColor =  colorService.createColor(color);
        color.setName("blue");
        testColor = colorService.updateColor(id, color);
        // Assert
        verify(colorRepository).existsById(id);

        assertEquals(id, testColor.getId());
        assertEquals("blue", testColor.getName());
    }

    @Test
    void canNotUpdateColor() {
        // Arrange
        Long id = 1L;
        Color color = Color.builder().id(id).name("red").build();
        // Act
        color = colorService.updateColor(id, color);
        // Assert
        assertNull(color);
    }

    @Test
    void canDeleteColor() {
        // Arrange
        Long id = 1L;
        // Act
        colorService.deleteColor(id);
        // Assert
        verify(colorRepository).deleteById(id);
    }
}
