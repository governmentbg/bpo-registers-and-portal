package bg.duosoft.bpo.registers.nonentity.filter;

import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 11.11.2024
 * Time: 14:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VwAgentFilter extends EExtraDataFilter {
    private String name;
    private String agentCode;
    private Integer status;
    private List<RepresentativeType> representativeTypeList;
}
