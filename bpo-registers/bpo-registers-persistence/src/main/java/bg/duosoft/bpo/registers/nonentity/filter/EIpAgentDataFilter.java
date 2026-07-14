package bg.duosoft.bpo.registers.nonentity.filter;

import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EIpAgentDataFilter extends EExtraDataFilter {
    private String name;
    private String ipoArea;
    private String agentCode;
    private Integer status;
    private String agentSpeciality;
    private String city;
    private String language;
    List<RepresentativeType> representativeTypeList;
}
