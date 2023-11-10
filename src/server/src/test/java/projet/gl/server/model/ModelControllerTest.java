package projet.gl.server.model;

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
import java.util.Optional;

@WebMvcTest(controllers = ModelController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Import(AuthTestConfiguration.class)
class ModelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModelService modelService;

    @Test
    void canGetAllModels() throws Exception {
        // Arrange
        Model model1 = Model.builder().id(1L).name("yaris").build();
        Model model2 = Model.builder().id(2L).name("series 1").build();
        Model model3 = Model.builder().id(3L).name("a1").build();
        List<Model> models = new ArrayList<>(Arrays.asList(model1, model2, model3));

        // Act
        Mockito.when(modelService.getAllModels()).thenReturn(models);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/models")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(3)));
    }

    @Test
    void canGetModelById() throws Exception {
        // Arrange
        Model model1 = Model.builder().id(1L).name("yaris").build();

        // Act
        Mockito.when(modelService.getModelById(1L)).thenReturn(Optional.ofNullable(model1));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/models/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("yaris")));
    }

    @Test
    void canCreateModel() throws Exception {
        // Act
        BDDMockito.given(modelService.createModel(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/models")
                .content("{\"name\": \"yaris\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("yaris")));
    }

    @Test
    void canUpdateModel() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        BDDMockito.given(modelService.updateModel(eq(id), ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(1)));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .put("/models/1")
                .content("{\"name\": \"yaris\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("yaris")));
    }

    @Test
    void canNotUpdateModel() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        BDDMockito.given(modelService.updateModel(eq(id), ArgumentMatchers.any())).willReturn(null);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .put("/models/1")
                .content("{\"name\": \"yaris\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void canDeleteModel() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        Mockito.doNothing().when(modelService).deleteModel(id);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/models/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}