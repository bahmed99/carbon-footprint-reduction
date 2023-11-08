package projet.gl.server.configuration;

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
class ConfigurationServiceTest {
    @Mock
    private ConfigurationRepository configurationRepository;

    @Mock
    private Optional<Configuration> configurationData;

    private AutoCloseable autoCloseable;

    private ConfigurationService configurationService;

    @BeforeEach
    void setUp() {
        autoCloseable = org.mockito.MockitoAnnotations.openMocks(this);
        configurationService = new ConfigurationService(configurationRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllConfigurations() {
        // Act
        configurationService.getAllConfigurations();
        // Assert
        verify(configurationRepository).findAll();
    }

    @Test
    void canGetConfigurationById() {
        // Arrange
        Long id = 1L;
        // Act
        configurationService.getConfigurationById(id);
        // Assert
        verify(configurationRepository).findById(id);
    }

    @Test
    void canCreateConfiguration() {
        // Arrange
        Configuration configuration = Configuration.builder().name("red").build();
        // Act
        configurationService.createConfiguration(configuration);
        // Assert
        verify(configurationRepository).save(configuration);
    }

    @Test
    void canUpdateConfiguration() {
        // Arrange
        Long id = 1L;
        Configuration configuration = Configuration.builder().id(id).name("VE").build();
        when(configurationRepository.findById(id)).thenReturn(configurationData);
        when(configurationData.isPresent()).thenReturn(true);
        when(configurationData.get()).thenReturn(configuration);
        when(configurationRepository.save(configuration)).thenReturn(configuration);
        // Act
        Configuration testConfiguration =  configurationService.createConfiguration(configuration);
        configuration.setName("airbag");
        testConfiguration = configurationService.updateConfiguration(id, configuration);
        // Assert
        verify(configurationData).isPresent();

        assertEquals(id, testConfiguration.getId());
        assertEquals("airbag", testConfiguration.getName());
    }

    @Test
    void canNotUpdateConfiguration() {
        // Arrange
        Long id = 1L;
        Configuration configuration = Configuration.builder().id(id).name("red").build();
        // Act
        configuration = configurationService.updateConfiguration(id, configuration);
        // Assert
        assertNull(configuration);
    }

    @Test
    void canDeleteConfiguration() {
        // Arrange
        Long id = 1L;
        Configuration configuration = Configuration.builder().id(id).name("VE").build();
        when(configurationRepository.existsById(id)).thenReturn(true);
        // Act
        configurationService.createConfiguration(configuration);
        boolean result = configurationService.deleteConfiguration(id);
        // Assert
        verify(configurationRepository).deleteById(id);
        
        assertEquals(true, result);
    }

    @Test
    void canNotDeleteConfiguration() {
        // Arrange
        Long id = 1L;
        // Act
        boolean result = configurationService.deleteConfiguration(id);
        // Assert
        assertEquals(false, result);
    }
}
