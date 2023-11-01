package projet.gl.server.vehicle;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehiculeFilterDTO {
    private List<Long> colorIds;
    private List<Long> modelIds;
    private List<Long> brandIds;
    private List<Long> configurationIds;
}
