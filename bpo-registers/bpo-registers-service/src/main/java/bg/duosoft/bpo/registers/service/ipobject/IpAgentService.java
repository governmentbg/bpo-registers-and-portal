package bg.duosoft.bpo.registers.service.ipobject;

import bg.duosoft.bpo.registers.dto.filter.AgentFilter;
import bg.duosoft.bpo.registers.dto.ipobject.IpAgentDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonDTO;
import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IpAgentService {
    Page<IpPersonDTO> filterAgents(AgentFilter filter);
    IpPersonDTO getByAgentCodeAndTypeIn(String agentCode, List<String> representativeTypes);
    List<IpPersonDTO> getAgentRelationsByAgentCode(String agentCode);
    List<IpPersonDTO> getAgentRelationsByAgentId(Integer agentId, String representativeType);
    List<String> filterAgentCities(String language, String cityName);
    List<String> filterAgentSpecialities(String language, String speciality);
    IpPersonDTO getByAgentCode(String agentCode);
    List<IpPersonDTO> filterAgentsByNameOrAgentCode(String nameOrAgentCode);
    List<IpPersonDTO> getBatchOfAgents(Long[] ids);
}
