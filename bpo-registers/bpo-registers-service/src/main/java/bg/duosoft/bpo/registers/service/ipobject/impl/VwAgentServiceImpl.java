package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.registers.dto.filter.VwAgentFilter;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonDTO;
import bg.duosoft.bpo.registers.entity.ipobject.VwAgent;
import bg.duosoft.bpo.registers.mapper.ipobject.IpAgentMapper;
import bg.duosoft.bpo.registers.mapper.ipobject.IpPersonMapper;
import bg.duosoft.bpo.registers.mapper.ipobject.VwAgentMapper;
import bg.duosoft.bpo.registers.mapper.ipobject.search.VwAgentFilterMapper;
import bg.duosoft.bpo.registers.repository.ipobject.VwAgentRepository;
import bg.duosoft.bpo.registers.service.ipobject.VwAgentService;
import bg.duosoft.bpo.registers.service.search.SearchServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 11.11.2024
 * Time: 13:34
 */
@Service
@RequiredArgsConstructor
public class VwAgentServiceImpl extends SearchServiceBase implements VwAgentService {
    private final VwAgentRepository repository;
    private final VwAgentMapper mapper;
    private final IpPersonMapper personMapper;
    private final VwAgentFilterMapper filterMapper;
    public Page<IpPersonDTO> searchAgents(VwAgentFilter filter) {
        bg.duosoft.bpo.registers.nonentity.filter.VwAgentFilter eFilter = filterMapper.toFilter(filter);
        List<IpPersonDTO> res = mapper.toDtoList(repository.findAgents(eFilter));
        Integer agentsCount = repository.countAgents(eFilter);
        Pageable pageable = getPage(filter);
        return new PageImpl<>(res, pageable, agentsCount);

    }
    public IpPersonDTO getByAgentCode(String agentCode) {
        VwAgent rec = repository.findByAgentCode(agentCode);
        return rec == null ? null : mapper.toDto(rec);
    }

    @Override
    public List<IpPersonDTO> getMatchingAgents(IpPersonDTO agent, Integer maxCount, boolean strongMatch) {
        List<VwAgent> res = repository.getMatchingAgents(personMapper.toEntity(agent), maxCount, strongMatch);
        return mapper.toDtoList(res);
    }
}
