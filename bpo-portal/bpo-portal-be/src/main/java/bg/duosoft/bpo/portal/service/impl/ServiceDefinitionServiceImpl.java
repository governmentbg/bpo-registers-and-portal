package bg.duosoft.bpo.portal.service.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.portal.dto.ServiceDefinition;
import bg.duosoft.bpo.portal.mapper.ServiceDefinitionMapper;
import bg.duosoft.bpo.portal.repository.ServiceDefinitionRepository;
import bg.duosoft.bpo.portal.service.ServiceDefinitionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceDefinitionServiceImpl extends CrudServiceBaseImpl<String, ServiceDefinition> implements ServiceDefinitionService {

    private final ServiceDefinitionRepository serviceDefinitionRepository;
    private final ServiceDefinitionMapper serviceDefinitionMapper;

    @Override
    protected ServiceDefinitionRepository getRepository() {
        return serviceDefinitionRepository;
    }

    @Override
    protected ServiceDefinitionMapper getMapper() {
        return serviceDefinitionMapper;
    }

    @Override
    protected Validator<ServiceDefinition> getValidator() {
        return null;
    }

    @Override
    protected boolean isSortable() {
        return true;
    }

    @Override
    protected String getSortColumn() {
        return "id";
    }

// TODO - make it cacheable after definitions are ready
//    @Override
//    public boolean isCacheable() {
//        return true;
//    }
}
