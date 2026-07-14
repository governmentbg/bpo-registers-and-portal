package bg.duosoft.bpo.registers.service.ipobject;

import bg.duosoft.bpo.registers.dto.filter.VwAgentFilter;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 11.11.2024
 * Time: 13:49
 * Service that returns real agents + all the representatives (lawyers and so on), which are treated like agents
 */
public interface VwAgentService {
    public Page<IpPersonDTO> searchAgents(VwAgentFilter filter);
    public IpPersonDTO getByAgentCode(String agentCode);
    public List<IpPersonDTO> getMatchingAgents(IpPersonDTO agent, Integer maxCount, boolean strongMatch);
}
