package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 14.11.2024
 * Time: 14:11
 */
@Data
public class LocarnoClassDTO implements BaseDTO<String> {
    private String id;
    private String headingId;
    private String classId;
    private String description;
}
