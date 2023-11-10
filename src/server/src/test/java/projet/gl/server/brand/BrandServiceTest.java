package projet.gl.server.brand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import projet.gl.server.model.ModelDTO;
import projet.gl.server.model.ModelRepository;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {
    @Mock
    private BrandRepository brandRepository;

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private Optional<Brand> brandData;

    private AutoCloseable autoCloseable;

    private BrandService brandService;

    @BeforeEach
    void setUp() {
        autoCloseable = org.mockito.MockitoAnnotations.openMocks(this);
        brandService = new BrandService(brandRepository, modelRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllBrands() {
        // Act
        brandService.getAllBrands();
        // Assert
        verify(brandRepository).findAll();
    }

    @Test
    void canGetBrandById() {
        // Arrange
        Long id = 1L;
        // Act
        brandService.getBrandById(id);
        // Assert
        verify(brandRepository).findById(id);
    }

    @Test
    void canCreateBrand() {
        // Arrange
        Brand brand = Brand.builder().name("toyota").build();
        // Act
        brandService.createBrand(brand);
        // Assert
        verify(brandRepository).save(brand);
    }

    @Test
    void canUpdateBrand() {
        // Arrange
        Long id = 1L;
        Brand brand = Brand.builder().id(id).name("bmw").build();
        when(brandRepository.findById(id)).thenReturn(brandData);
        when(brandData.isPresent()).thenReturn(true);
        when(brandData.get()).thenReturn(brand);
        when(brandRepository.save(brand)).thenReturn(brand);
        // Act
        Brand testBrand =  brandService.createBrand(brand);
        brand.setName("audi");
        testBrand = brandService.updateBrand(id, brand);
        // Assert
        verify(brandData).isPresent();

        assertEquals(id, testBrand.getId());
        assertEquals("audi", testBrand.getName());
    }

    @Test
    void canNotUpdateBrand() {
        // Arrange
        Long id = 1L;
        Brand brand = Brand.builder().id(id).name("toyota").build();
        // Act
        brand = brandService.updateBrand(id, brand);
        // Assert
        assertNull(brand);
    }

    @Test
    void canDeleteBrand() {
        // Arrange
        Long id = 1L;
        // Act
        brandService.deleteBrand(id);
        // Assert
        verify(brandRepository).deleteById(id);
    }

    @Test
    void canGetModelsByBrand() {
        // Arrange
        Long id = 1L;
        List<ModelDTO> models = new ArrayList<>();
        models.add(new ModelDTO(1L, "model1", LocalDate.now(), LocalDate.now(), id));
        models.add(new ModelDTO(2L, "model2", LocalDate.now(), LocalDate.now(), id));
        when(brandRepository.findById(id)).thenReturn(brandData);
        when(brandData.isPresent()).thenReturn(true);
        when(modelRepository.findModelsByBrand(id)).thenReturn(models);
        // Act
        List<ModelDTO> returnedModels = brandService.getModelsByBrand(id);
        // Assert
        verify(brandData).isPresent();

        assertEquals(models.size(), returnedModels.size());
    }

    @Test
    void canNotGetModelsByBrand() {
        // Arrange
        Long id = 1L;
        // Act
        List<ModelDTO> models = brandService.getModelsByBrand(id);
        // Assert
        assertEquals(0, models.size());
    }
}