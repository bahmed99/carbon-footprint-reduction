package projet.gl.server.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelDTO {

    private Long id;
    private String name;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Long brandId;

    public ModelDTO(Model model) {
        this.id = model.getId();
        this.name = model.getName();
        this.createdAt = model.getCreatedAt();
        this.updatedAt = model.getUpdatedAt();
        this.brandId = model.getBrandId();
    }

}