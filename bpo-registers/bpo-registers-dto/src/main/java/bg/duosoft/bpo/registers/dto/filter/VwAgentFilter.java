package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.BaseFilterDTO;
import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class VwAgentFilter extends BaseFilterDTO {
    private String name;
    private String agentCode;
    private Integer status;
    List<RepresentativeType> representativeTypeList;
}
