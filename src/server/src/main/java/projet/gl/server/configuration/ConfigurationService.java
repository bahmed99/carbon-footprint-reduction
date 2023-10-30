package projet.gl.server.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public List<Configuration> getAllConfigurations() {
        return configurationRepository.findAll();
    }

    public Configuration getConfigurationById(Long id) {
        Optional<Configuration> configuration = configurationRepository.findById(id);
        return configuration.orElse(null);
    }

    public Configuration createConfiguration(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    public Configuration updateConfiguration(Long id, Configuration newConfiguration) {
        Optional<Configuration> existingConfiguration = configurationRepository.findById(id);
        if (existingConfiguration.isPresent()) {
            Configuration configuration = existingConfiguration.get();
            configuration.setName(newConfiguration.getName());
            return configurationRepository.save(configuration);
        }
        return null;
    }

    public boolean deleteConfiguration(Long id) {
        if (configurationRepository.existsById(id)) {
            configurationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
