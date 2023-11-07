package projet.gl.server.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ModelServiceTest {
    @Mock
    private ModelRepository modelRepository;

    @Mock
    private Optional<Model> modelData;

    private AutoCloseable autoCloseable;

    private ModelService modelService;

    @BeforeEach
    void setUp() {
        autoCloseable = org.mockito.MockitoAnnotations.openMocks(this);
        modelService = new ModelService(modelRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllModels() {
        // Act
        modelService.getAllModels();
        // Assert
        verify(modelRepository).findAll();
    }

    @Test
    void canGetModelById() {
        // Arrange
        Long id = 1L;
        // Act
        modelService.getModelById(id);
        // Assert
        verify(modelRepository).findById(id);
    }

    @Test
    void canCreateModel() {
        // Arrange
        Model model = Model.builder().name("yaris").build();
        // Act
        modelService.createModel(model);
        // Assert
        verify(modelRepository).save(model);
    }

    @Test
    void canUpdateModel() {
        // Arrange
        Long id = 1L;
        Model model = Model.builder().id(id).name("series 1").build();
        when(modelRepository.findById(id)).thenReturn(modelData);
        when(modelData.isPresent()).thenReturn(true);
        when(modelData.get()).thenReturn(model);
        when(modelRepository.save(model)).thenReturn(model);
        // Act
        Model testModel =  modelService.createModel(model);
        model.setName("series 2");
        testModel = modelService.updateModel(id, model);
        // Assert
        verify(modelData).isPresent();

        assertEquals(id, testModel.getId());
        assertEquals("series 2", testModel.getName());
    }

    @Test
    void canNotUpdateModel() {
        // Arrange
        Long id = 1L;
        Model model = Model.builder().id(id).name("yaris").build();
        // Act
        model = modelService.updateModel(id, model);
        // Assert
        assertNull(model);
    }

    @Test
    void canDeleteModel() {
        // Arrange
        Long id = 1L;
        // Act
        modelService.deleteModel(id);
        // Assert
        verify(modelRepository).deleteById(id);
    }
}