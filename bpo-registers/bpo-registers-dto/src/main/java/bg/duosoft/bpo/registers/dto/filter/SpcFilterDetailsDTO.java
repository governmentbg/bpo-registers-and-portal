package bg.duosoft.bpo.registers.dto.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.02.2024
 * Time: 16:42
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpcFilterDetailsDTO extends PatentLikeFilterDetailsDTO {
}
