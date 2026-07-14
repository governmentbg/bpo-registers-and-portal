package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.BaseFilterDTO;
import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AgentFilter extends BaseFilterDTO {
    private String name;
    private String ipoArea;
    private String agentCode;
    private Integer status;
    private String agentSpeciality;
    private String city;
    private String language;
    List<RepresentativeType> representativeTypeList;
}
