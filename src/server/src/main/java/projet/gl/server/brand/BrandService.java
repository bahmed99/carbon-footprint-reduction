package projet.gl.server.brand;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.gl.server.model.ModelDTO;
import projet.gl.server.model.ModelRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    private final ModelRepository modelRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Long id, Brand updatedBrand) {
        Optional<Brand> existingBrand = brandRepository.findById(id);

        if (existingBrand.isPresent()) {
            Brand brandToUpdate = existingBrand.get();

            BeanUtils.copyProperties(updatedBrand, brandToUpdate, "id", "createdAt", "updatedAt", "models");

            brandToUpdate.setId(id);
            brandToUpdate.setCreatedAt(existingBrand.get().getCreatedAt());
            brandToUpdate.setUpdatedAt(LocalDate.now());

            return brandRepository.save(brandToUpdate);
        } else {
            return null;
        }
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    public List<ModelDTO> getModelsByBrand(Long brandId) {
        Optional<Brand> brand = brandRepository.findById(brandId);

        if (brand.isPresent()) {
            return modelRepository.findModelsByBrand(brandId);
        } else {
            return Collections.emptyList();
        }
    }

}
