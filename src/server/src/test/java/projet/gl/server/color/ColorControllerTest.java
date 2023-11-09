package projet.gl.server.color;

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

@WebMvcTest(controllers = ColorController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Import(AuthTestConfiguration.class)
class ColorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ColorService colorService;

    @Test
    void canGetAllColors() throws Exception {
        // Arrange
        Color color1 = Color.builder().id(1L).name("red").build();
        Color color2 = Color.builder().id(2L).name("blue").build();
        Color color3 = Color.builder().id(3L).name("green").build();
        List<Color> colors = new ArrayList<>(Arrays.asList(color1, color2, color3));

        // Act
        Mockito.when(colorService.getAllColors()).thenReturn(colors);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/colors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(3)));
    }

    @Test
    void canGetColorById() throws Exception {
        // Arrange
        Color color1 = Color.builder().id(1L).name("red").build();

        // Act
        Mockito.when(colorService.getColorById(1L)).thenReturn(Optional.ofNullable(color1));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/colors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("red")));
    }

    @Test
    void canCreateColor() throws Exception {
        // Act
        BDDMockito.given(colorService.createColor(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/colors")
                .content("{\"name\": \"red\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("red")));
    }

    @Test
    void canUpdateColor() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        BDDMockito.given(colorService.updateColor(eq(id), ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(1)));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .put("/colors/1")
                .content("{\"name\": \"red\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("red")));
    }

    @Test
    void canNotUpdateColor() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        BDDMockito.given(colorService.updateColor(eq(id), ArgumentMatchers.any())).willReturn(null);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .put("/colors/1")
                .content("{\"name\": \"red\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void canDeleteColor() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        Mockito.doNothing().when(colorService).deleteColor(id);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/colors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
