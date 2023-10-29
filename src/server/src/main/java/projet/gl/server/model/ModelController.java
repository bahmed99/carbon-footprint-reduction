package projet.gl.server.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import projet.gl.server.brand.Brand;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public ResponseEntity<List<ModelDTO>> getAllModels() {
        List<ModelDTO> modelDTOs = modelService.getAllModels().stream()
                .map(this::convertToModelDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(modelDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelDTO> getModelById(@PathVariable Long id) {
        return modelService.getModelById(id)
                .map(model -> new ResponseEntity<>(convertToModelDTO(model), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ModelDTO> createModel(@RequestBody ModelDTO modelDTO) {
        Model model = convertToModel(modelDTO);
        Model createdModel = modelService.createModel(model);
        return new ResponseEntity<>(convertToModelDTO(createdModel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable Long id, @RequestBody Model updatedModel) {
        Model updated = modelService.updateModel(id, updatedModel);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ModelDTO convertToModelDTO(Model model) {
        ModelDTO modelDTO = new ModelDTO();
        modelDTO.setId(model.getId());
        modelDTO.setName(model.getName());
        modelDTO.setCreatedAt(model.getCreatedAt());
        modelDTO.setUpdatedAt(model.getUpdatedAt());
        modelDTO.setBrandId(model.getBrandId());
        return modelDTO;
    }

    private Model convertToModel(ModelDTO modelDTO) {
        Model model = new Model();
        model.setName(modelDTO.getName());
        model.setBrandId(modelDTO.getBrandId());
        // Set other fields...
        return model;
    }
}
