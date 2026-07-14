package bg.duosoft.bpo.registers.repository.ipobject.custom;

import bg.duosoft.bpo.registers.entity.ipobject.EIpPerson;
import bg.duosoft.bpo.registers.nonentity.filter.EIpAgentDataFilter;
import java.util.List;

public interface IpAgentRepositoryCustom {
    List<EIpPerson> filterAgents(EIpAgentDataFilter filter);
    Integer countAgents(EIpAgentDataFilter filter);
}
