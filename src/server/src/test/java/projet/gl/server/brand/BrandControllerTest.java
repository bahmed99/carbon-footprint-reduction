package projet.gl.server.brand;

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
import projet.gl.server.model.ModelDTO;

import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = BrandController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Import(AuthTestConfiguration.class)
class BrandControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    @Test
    void canGetAllBrands() throws Exception {
        // Arrange
        Brand brand1 = Brand.builder().id(1L).name("toyota").build();
        Brand brand2 = Brand.builder().id(2L).name("bmw").build();
        Brand brand3 = Brand.builder().id(3L).name("audi").build();
        List<Brand> brands = new ArrayList<>(Arrays.asList(brand1, brand2, brand3));

        // Act
        Mockito.when(brandService.getAllBrands()).thenReturn(brands);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/brands")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(3)));
    }

    @Test
    void canGetBrandById() throws Exception {
        // Arrange
        Brand brand1 = Brand.builder().id(1L).name("toyota").build();

        // Act
        Mockito.when(brandService.getBrandById(1L)).thenReturn(Optional.ofNullable(brand1));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/brands/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("toyota")));
    }

    @Test
    void canCreateBrand() throws Exception {
        // Act
        BDDMockito.given(brandService.createBrand(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/brands")
                .content("{\"name\": \"toyota\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("toyota")));
    }

    @Test
    void canUpdateBrand() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        BDDMockito.given(brandService.updateBrand(eq(id), ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(1)));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .put("/brands/1")
                .content("{\"name\": \"toyota\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("toyota")));
    }

    @Test
    void canNotUpdateBrand() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        BDDMockito.given(brandService.updateBrand(eq(id), ArgumentMatchers.any())).willReturn(null);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .put("/brands/1")
                .content("{\"name\": \"toyota\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void canDeleteBrand() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        Mockito.doNothing().when(brandService).deleteBrand(id);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/brands/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void canGetModelsByBrand() throws Exception {
        // Arrange
        ModelDTO model1 = new ModelDTO();
        ModelDTO model2 = new ModelDTO();
        ModelDTO model3 = new ModelDTO();
        List<ModelDTO> models = new ArrayList<>(Arrays.asList(model1, model2, model3));

        // Act
        Mockito.when(brandService.getModelsByBrand(1L)).thenReturn(models);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/brands/1/models")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(3)));
    }
}