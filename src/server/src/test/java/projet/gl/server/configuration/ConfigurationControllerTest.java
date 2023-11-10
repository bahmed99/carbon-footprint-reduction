package projet.gl.server.configuration;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import projet.gl.server.AuthTestConfiguration;

import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(controllers = ConfigurationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Import(AuthTestConfiguration.class)
class ConfigurationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConfigurationService configurationService;

    @Test
    void canGetAllConfigurations() throws Exception {
        // Arrange
        Configuration configuration1 = Configuration.builder().id(1L).name("VE").build();
        Configuration configuration2 = Configuration.builder().id(2L).name("Airbag").build();
        List<Configuration> configurations = new ArrayList<>(Arrays.asList(configuration1, configuration2));

        // Act
        Mockito.when(configurationService.getAllConfigurations()).thenReturn(configurations);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/configurations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(2)));
    }

    @Test
    void canGetConfigurationById() throws Exception {
        // Arrange
        Configuration configuration1 = Configuration.builder().id(1L).name("Airbag").build();

        // Act
        Mockito.when(configurationService.getConfigurationById(1L)).thenReturn(configuration1);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/configurations/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("Airbag")));
    }

    @Test
    void canNotGetConfigurationById() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        Mockito.when(configurationService.getConfigurationById(id)).thenReturn(null);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/configurations/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void canCreateConfiguration() throws Exception {
        // Act
        BDDMockito.given(configurationService.createConfiguration(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/configurations")
                .content("{\"name\": \"Airbag\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("Airbag")));
    }

    @Test
    void canUpdateConfiguration() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        BDDMockito.given(configurationService.updateConfiguration(eq(id), ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(1)));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .put("/configurations/1")
                .content("{\"name\": \"Airbag\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("Airbag")));
    }

    @Test
    void canNotUpdateConfiguration() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        BDDMockito.given(configurationService.updateConfiguration(eq(id), ArgumentMatchers.any())).willReturn(null);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .put("/configurations/1")
                .content("{\"name\": \"Airbag\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void canDeleteConfiguration() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        Mockito.when(configurationService.deleteConfiguration(id)).thenReturn(true);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/configurations/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void canNotDeleteConfiguration() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        Mockito.when(configurationService.deleteConfiguration(id)).thenReturn(false);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/configurations/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}