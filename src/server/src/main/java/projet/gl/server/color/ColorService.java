package projet.gl.server.color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {
    private final ColorRepository colorRepository;

    @Autowired
    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public Optional<Color> getColorById(Long id) {
        return colorRepository.findById(id);
    }

    public Color createColor(Color color) {
        return colorRepository.save(color);
    }

    public Color updateColor(Long id, Color updatedColor) {
        if (colorRepository.existsById(id)) {
            updatedColor.setId(id); // Ensure the ID is set correctly
            return colorRepository.save(updatedColor);
        } else {
            // Handle the case where the color with the given ID doesn't exist
            // You can throw an exception or handle it as needed.
            return null;
        }
    }

    public void deleteColor(Long id) {
        colorRepository.deleteById(id);
    }
}
