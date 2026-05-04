package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.BaseFilterDTO;
import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 07.11.2024
 * Time: 15:14
 */
@Data
public class PersonFilter extends BaseFilterDTO {
    private String name;
    private Integer legalType;
}
