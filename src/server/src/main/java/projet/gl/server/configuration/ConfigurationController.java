package projet.gl.server.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @Autowired
    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping
    public List<Configuration> getAllConfigurations() {
        return configurationService.getAllConfigurations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Configuration> getConfigurationById(@PathVariable Long id) {
        Configuration configuration = configurationService.getConfigurationById(id);
        if (configuration != null) {
            return ResponseEntity.ok(configuration);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Configuration> createConfiguration(@RequestBody Configuration configuration) {
        Configuration createdConfiguration = configurationService.createConfiguration(configuration);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConfiguration);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Configuration> updateConfiguration(@PathVariable Long id, @RequestBody Configuration configuration) {
        Configuration updatedConfiguration = configurationService.updateConfiguration(id, configuration);
        if (updatedConfiguration != null) {
            return ResponseEntity.ok(updatedConfiguration);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConfiguration(@PathVariable Long id) {
        boolean deleted = configurationService.deleteConfiguration(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
