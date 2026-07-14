package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.AgentSpecialityDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.AgentSpecialityMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.AgentSpecialityRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.AgentSpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgentSpecialityServiceImpl extends CrudServiceBaseImpl<String, AgentSpecialityDTO> implements AgentSpecialityService {

    private final AgentSpecialityMapper agentSpecialityMapper;
    private final AgentSpecialityRepository agentSpecialityRepository;

    @Override
    protected AgentSpecialityRepository getRepository() {
        return agentSpecialityRepository;
    }

    @Override
    protected AgentSpecialityMapper getMapper() {
        return agentSpecialityMapper;
    }

    @Override
    protected Validator<AgentSpecialityDTO> getValidator() {
        return null;
    }

    @Override
    public boolean isCacheable() {
        return true;
    }

}
