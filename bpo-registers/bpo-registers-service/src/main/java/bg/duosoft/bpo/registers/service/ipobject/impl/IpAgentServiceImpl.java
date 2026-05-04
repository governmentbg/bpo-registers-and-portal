package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import bg.duosoft.bpo.registers.dto.filter.AgentFilter;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPerson;
import bg.duosoft.bpo.registers.mapper.ipobject.IpPersonMapper;
import bg.duosoft.bpo.registers.mapper.ipobject.search.AgentFilterDetailsMapper;
import bg.duosoft.bpo.registers.nonentity.filter.EIpAgentDataFilter;
import bg.duosoft.bpo.registers.repository.ipobject.IpAgentRepository;
import bg.duosoft.bpo.registers.repository.ipobject.IpPersonRepository;
import bg.duosoft.bpo.registers.service.ipobject.IpAgentService;
import bg.duosoft.bpo.registers.service.search.SearchServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IpAgentServiceImpl extends SearchServiceBase implements IpAgentService {

    private final AgentFilterDetailsMapper agentFilterDetailsMapper;
    private final IpAgentRepository ipAgentRepository;
    private final IpPersonRepository ipPersonRepository;
    private final IpPersonMapper ipPersonMapper;

    @Override
    public Page<IpPersonDTO> filterAgents(AgentFilter filter) {
        EIpAgentDataFilter ipAgentFilter = agentFilterDetailsMapper.toIpAgentFilter(filter);
        List<EIpPerson> eIpAgents = ipAgentRepository.filterAgents(ipAgentFilter);
        List<IpPersonDTO> agents = ipPersonMapper.toDtoList(eIpAgents);
        Integer agentsCount = ipAgentRepository.countAgents(ipAgentFilter);
        Pageable pageable = getPage(filter);
        return new PageImpl<>(agents, pageable, agentsCount);
    }

    @Override
    public IpPersonDTO getByAgentCodeAndTypeIn(String agentCode, List<String> representativeTypes) {
        return ipPersonMapper.toDto(ipPersonRepository.getAgentByAgentCodeAndTypeIn(agentCode, representativeTypes));
    }

    @Override
    public List<IpPersonDTO> getAgentRelationsByAgentCode(String agentCode) {
        IpPersonDTO ipPersonDTO = getByAgentCode(agentCode);
        if (ipPersonDTO != null) {
            return getAgentRelationsByAgentId(ipPersonDTO.getAgent().getId(), ipPersonDTO.getAgent().getRepresentativeType().getId());
        }
        return Collections.emptyList();
    }

    @Override
    public List<IpPersonDTO> getAgentRelationsByAgentId(Integer agentId, String representativeType) {
        if (RepresentativeType.AGENT.code().equals(representativeType)) {
            return ipPersonMapper.toDtoList(ipPersonRepository.getAgentPartnerships(agentId));
        } else {
            return ipPersonMapper.toDtoList(ipPersonRepository.getPartnershipAgents(agentId));
        }
    }

    @Override
    public List<String> filterAgentCities(String language, String cityName) {
        List<String> cities = ipAgentRepository.filterAgentCities(language, "%" + cityName + "%");
        if (!CollectionUtils.isEmpty(cities)) {
            Collections.sort(cities);
        }
        return cities;
    }

    @Override
    public List<String> filterAgentSpecialities(String language, String speciality) {
        return ipAgentRepository.filterAgentSpecialities(language, "%" + speciality + "%");
    }

    @Override
    public IpPersonDTO getByAgentCode(String agentCode) {
        //using the filterAgents method to filter agent by code
        AgentFilter filter = new AgentFilter();
        filter.setAgentCode(agentCode);
        List<EIpPerson> res = ipAgentRepository.filterAgents(agentFilterDetailsMapper.toIpAgentFilter(filter));
        return ObjectUtils.isEmpty(res) ? null : ipPersonMapper.toDto(res.get(0));
    }

    @Override
    public List<IpPersonDTO> filterAgentsByNameOrAgentCode(String nameOrAgentCode) {
        return ipPersonMapper.toDtoList(ipPersonRepository.filterByNameOrAgentCode(nameOrAgentCode));
    }

    @Override
    public List<IpPersonDTO> getBatchOfAgents(Long[] ids) {
        return ipPersonMapper.toDtoList(ipPersonRepository.findByIdIn(ids));
    }
}
