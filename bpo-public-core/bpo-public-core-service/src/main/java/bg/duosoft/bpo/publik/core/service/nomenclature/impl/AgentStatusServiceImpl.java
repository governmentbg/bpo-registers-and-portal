package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.AgentStatusDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.AgentStatusMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.AgentStatusRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.AgentStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgentStatusServiceImpl extends CrudServiceBaseImpl<Integer, AgentStatusDTO> implements AgentStatusService {

    private final AgentStatusRepository agentStatusRepository;
    private final AgentStatusMapper agentStatusMapper;

    @Override
    protected AgentStatusRepository getRepository() {
        return agentStatusRepository;
    }

    @Override
    protected AgentStatusMapper getMapper() {
        return agentStatusMapper;
    }

    @Override
    protected Validator<AgentStatusDTO> getValidator() {
        return null;
    }

    @Override
    public boolean isCacheable() {
        return true;
    }
}
