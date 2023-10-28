package projet.gl.server.brand;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
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
}
