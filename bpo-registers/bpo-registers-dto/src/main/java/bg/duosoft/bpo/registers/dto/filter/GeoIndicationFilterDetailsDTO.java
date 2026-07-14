package bg.duosoft.bpo.registers.dto.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.02.2024
 * Time: 11:17
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoIndicationFilterDetailsDTO extends MarkLikeFilterDetailsDTO {
}
