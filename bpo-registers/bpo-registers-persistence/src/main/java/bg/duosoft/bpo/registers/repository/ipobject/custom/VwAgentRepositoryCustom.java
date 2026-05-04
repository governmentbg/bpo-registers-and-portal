package bg.duosoft.bpo.registers.repository.ipobject.custom;

import bg.duosoft.bpo.registers.entity.ipobject.EIpPerson;
import bg.duosoft.bpo.registers.entity.ipobject.VwAgent;
import bg.duosoft.bpo.registers.nonentity.filter.VwAgentFilter;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 11.11.2024
 * Time: 14:12
 */
public interface VwAgentRepositoryCustom {
    public List<VwAgent> findAgents(VwAgentFilter filter);
    public Integer countAgents(VwAgentFilter filter);
    List<VwAgent> getMatchingAgents(EIpPerson agent, Integer maxCount, boolean strongMatch);
}
