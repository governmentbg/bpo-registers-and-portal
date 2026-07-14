package bg.duosoft.bpo.registers.dto.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.02.2024
 * Time: 11:43
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarkLikeFilterDetailsDTO extends CommonIpObjectFilterDetailsDTO {

    private List<String> objectSubtype;
    private NiceClassesFilterDetailsDTO niceClasses;
    private ViennaClassesFilterDetailsDTO viennaClasses;
}
