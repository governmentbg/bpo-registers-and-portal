package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.service.nomenclature.AgentStatusService;
import bg.duosoft.bpo.publik.core.service.nomenclature.RepresentativeTypeService;
import bg.duosoft.bpo.registers.dto.ipobject.IpAgentDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonDTO;
import bg.duosoft.bpo.registers.entity.ipobject.VwAgent;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: ggeorgiev
 * Date: 11.11.2024
 * Time: 13:36
 */
@Mapper(componentModel =  "spring")
public abstract class VwAgentMapper extends BaseObjectMapper<VwAgent, IpPersonDTO> {
    @Autowired
    private IpPersonMapper personMapper;
    @Autowired
    private RepresentativeTypeService representativeTypeService;
    @Autowired
    private AgentStatusService agentStatusService;
    @Override
    public IpPersonDTO toDto(VwAgent vwAgent) {
        IpPersonDTO res = personMapper.toDto(vwAgent.getPerson());
        if (res.getAgent() == null) {
            IpAgentDTO a = new IpAgentDTO();
            a.setId(vwAgent.getId());
            a.setAgentCode(vwAgent.getAgentCode());
            a.setRepresentativeType(representativeTypeService.getById(vwAgent.getRepresentativeType()));
            a.setAgentStatus(agentStatusService.getById(vwAgent.getAgentStatus()));
            res.setAgent(a);
        }
        return res;
    }
}
