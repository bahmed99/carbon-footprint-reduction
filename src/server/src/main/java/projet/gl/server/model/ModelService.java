package projet.gl.server.model;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ModelService {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Optional<Model> getModelById(Long id) {
        return modelRepository.findById(id);
    }

    public Model createModel(Model model) {
        return modelRepository.save(model);
    }

    public Model updateModel(Long id, Model updatedModel) {
        Optional<Model> existingModel = modelRepository.findById(id);

        if (existingModel.isPresent()) {
            Model modelToUpdate = existingModel.get();

            BeanUtils.copyProperties(updatedModel, modelToUpdate, "id", "createdAt", "updatedAt", "brand");

            modelToUpdate.setId(id);
            modelToUpdate.setCreatedAt(existingModel.get().getCreatedAt());
            modelToUpdate.setUpdatedAt(LocalDate.now());

            return modelRepository.save(modelToUpdate);
        } else {
            return null;
        }
    }

    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }
}
