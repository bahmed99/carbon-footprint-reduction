package projet.gl.server.vehicle;

import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import projet.gl.server.AuthTestConfiguration;

@WebMvcTest(controllers = VehicleController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Import(AuthTestConfiguration.class)
class VehicleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Test
    void canGetAllVehicles() throws Exception {
        // Arrange
        Vehicle vehicle1 = Vehicle.builder().id(1L).yearOfCreation(2021).priceWithoutConfiguration(10000).build();
        Vehicle vehicle2 = Vehicle.builder().id(2L).yearOfCreation(2022).priceWithoutConfiguration(20000).build();
        Vehicle vehicle3 = Vehicle.builder().id(3L).yearOfCreation(2023).priceWithoutConfiguration(30000).build();
        List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(vehicle1, vehicle2, vehicle3));

        // Act
        Mockito.when(vehicleService.getAllVehicles()).thenReturn(vehicles);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(3)));
    }

    @Test
    void canGetVehicleById() throws Exception {
        // Arrange
        Vehicle vehicle1 = Vehicle.builder().id(1L).yearOfCreation(2021).priceWithoutConfiguration(10000).build();

        // Act
        Mockito.when(vehicleService.getVehicleById(1L)).thenReturn(Optional.ofNullable(vehicle1));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.yearOfCreation", CoreMatchers.is(2021)));
    }

    @Test
    void canCreateVehicle() throws Exception {
        // Arrange
        Vehicle vehicle1 = Vehicle.builder().id(1L).yearOfCreation(2021).priceWithoutConfiguration(10000).build();

        // Act
        Mockito.when(vehicleService.createVehicle(ArgumentMatchers.any())).thenReturn(vehicle1);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/vehicles/0")
                .content("{\"yearOfCreation\": 2021}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    void canCreateManyVehicles() throws Exception {
        // Arrange
        Vehicle vehicle1 = Vehicle.builder().id(1L).yearOfCreation(2021).priceWithoutConfiguration(10000).build();

        // Act
        Mockito.when(vehicleService.createVehicle(ArgumentMatchers.any())).thenReturn(vehicle1);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/vehicles/3")
                .content("{\"yearOfCreation\": 2021}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void canUpdateVehicle() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        BDDMockito.given(vehicleService.updateVehicle(eq(id), ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(1)));

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .put("/vehicles/1")
                .content("{\"yearOfCreation\": 2021}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.yearOfCreation", CoreMatchers.is(2021)));
    }

    @Test
    void canDeleteVehicle() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        Mockito.doNothing().when(vehicleService).deleteVehicle(id);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void canFindByFilters() throws Exception {
        // Arrange
        Vehicle vehicle1 = Vehicle.builder().id(1L).yearOfCreation(2021).priceWithoutConfiguration(10000).build();
        Vehicle vehicle2 = Vehicle.builder().id(2L).yearOfCreation(2022).priceWithoutConfiguration(20000).build();
        Vehicle vehicle3 = Vehicle.builder().id(3L).yearOfCreation(2023).priceWithoutConfiguration(30000).build();
        List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(vehicle1, vehicle2, vehicle3));

        // Act
        Mockito.when(vehicleService.findByFilters(ArgumentMatchers.any())).thenReturn(vehicles);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/vehicles/filters")
                .content("{\"yearOfCreation\": 2021}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(3)));
    }

    @Test
    void canFindByFiltersByBrand() throws Exception {
        // Arrange
        Vehicle vehicle1 = Vehicle.builder().id(1L).yearOfCreation(2021).priceWithoutConfiguration(10000).build();
        Vehicle vehicle2 = Vehicle.builder().id(2L).yearOfCreation(2022).priceWithoutConfiguration(20000).build();
        Vehicle vehicle3 = Vehicle.builder().id(3L).yearOfCreation(2023).priceWithoutConfiguration(30000).build();
        List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(vehicle1, vehicle2, vehicle3));

        // Act
        Mockito.when(vehicleService.findByFilters(ArgumentMatchers.any())).thenReturn(vehicles);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/vehicles/filters")
                .content("{\"brand\": \"brand\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(3)));
    }

    @Test
    void canFindByFiltersByColor() throws Exception {
        // Arrange
        Vehicle vehicle1 = Vehicle.builder().id(1L).yearOfCreation(2021).priceWithoutConfiguration(10000).build();
        Vehicle vehicle2 = Vehicle.builder().id(2L).yearOfCreation(2022).priceWithoutConfiguration(20000).build();
        Vehicle vehicle3 = Vehicle.builder().id(3L).yearOfCreation(2023).priceWithoutConfiguration(30000).build();
        List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(vehicle1, vehicle2, vehicle3));

        // Act
        Mockito.when(vehicleService.findByFilters(ArgumentMatchers.any())).thenReturn(vehicles);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/vehicles/filters")
                .content("{\"color\": \"color\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(3)));
    }

    @Test
    void canFindByFiltersByConfigurations() throws Exception {
        // Arrange
        Vehicle vehicle1 = Vehicle.builder().id(1L).yearOfCreation(2021).priceWithoutConfiguration(10000).build();
        Vehicle vehicle2 = Vehicle.builder().id(2L).yearOfCreation(2022).priceWithoutConfiguration(20000).build();
        Vehicle vehicle3 = Vehicle.builder().id(3L).yearOfCreation(2023).priceWithoutConfiguration(30000).build();
        List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(vehicle1, vehicle2, vehicle3));

        // Act
        Mockito.when(vehicleService.findByFilters(ArgumentMatchers.any())).thenReturn(vehicles);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/vehicles/filters")
                .content("{\"configurations\": \"configurations\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(3)));
    }

    @Test
    void canFindByFiltersByModel() throws Exception {
        // Arrange
        Vehicle vehicle1 = Vehicle.builder().id(1L).yearOfCreation(2021).priceWithoutConfiguration(10000).build();
        Vehicle vehicle2 = Vehicle.builder().id(2L).yearOfCreation(2022).priceWithoutConfiguration(20000).build();
        Vehicle vehicle3 = Vehicle.builder().id(3L).yearOfCreation(2023).priceWithoutConfiguration(30000).build();
        List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(vehicle1, vehicle2, vehicle3));

        // Act
        Mockito.when(vehicleService.findByFilters(ArgumentMatchers.any())).thenReturn(vehicles);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/vehicles/filters")
                .content("{\"model\": \"model\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(3)));
    }

    @Test
    void canFindByFiltersAndPageSize() throws Exception {
        // Arrange
        Vehicle vehicle1 = Vehicle.builder().id(1L).yearOfCreation(2021).priceWithoutConfiguration(10000).build();
        Vehicle vehicle2 = Vehicle.builder().id(2L).yearOfCreation(2022).priceWithoutConfiguration(20000).build();
        Vehicle vehicle3 = Vehicle.builder().id(3L).yearOfCreation(2023).priceWithoutConfiguration(30000).build();
        List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(vehicle1, vehicle2, vehicle3));
        Page<Vehicle> page = new PageImpl<>(vehicles);

        // Act
        Mockito.when(vehicleService.findByFiltersAndPageSize(ArgumentMatchers.any(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(page);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/vehicles/filters/page/1/1")
                .content("{\"yearOfCreation\": 2021}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(3)));
    }

    @Test
    void canCount() throws Exception {
        // Arrange
        Long count = 3L;

        // Act
        Mockito.when(vehicleService.countVehicles()).thenReturn(count);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/count")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is(3)));
    }

    @Test
    void canCountByModel() throws Exception {
        // Arrange
        List<Object[]> count = new ArrayList<>();

        // Act
        Mockito.when(vehicleService.countByModel()).thenReturn(count);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/countByModel")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(0)));
    }

    @Test
    void canCountByBrand() throws Exception {
        // Arrange
        List<Object[]> count = new ArrayList<>();

        // Act
        Mockito.when(vehicleService.countByBrand()).thenReturn(count);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/countByBrand")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(0)));
    }

    @Test
    void canCountByFilter() throws Exception {
        // Arrange
        Long count = 3L;

        // Act
        Mockito.when(vehicleService.countByFilter(ArgumentMatchers.any())).thenReturn(count);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/vehicles/countByFilter")
                .content("{\"yearOfCreation\": 2021}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is(3)));
    }
}
