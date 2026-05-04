package bg.duosoft.bpo.registers.dto.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlantBreedFilterDetailsDTO extends PatentLikeFilterDetailsDTO {
    private List<String> objectSubtype;
    private String latinClassification;
    private String bgClassification;
}
