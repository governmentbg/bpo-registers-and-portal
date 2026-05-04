package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 14.11.2024
 * Time: 14:12
 */
@Data
public class LocarnoHeadingDTO implements BaseDTO<String> {
    private String id;
    private String description;
    private List<LocarnoClassDTO> classes;
}
