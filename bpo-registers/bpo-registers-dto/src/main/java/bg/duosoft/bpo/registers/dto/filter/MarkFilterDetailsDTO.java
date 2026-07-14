package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.publik.core.dto.nomenclature.MarkKindDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.02.2024
 * Time: 17:55
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarkFilterDetailsDTO extends MarkLikeFilterDetailsDTO {

    private MarkKindDTO markKind;
}
