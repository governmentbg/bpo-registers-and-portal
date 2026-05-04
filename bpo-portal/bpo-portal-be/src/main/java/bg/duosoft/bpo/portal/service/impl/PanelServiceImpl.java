package bg.duosoft.bpo.portal.service.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.common.util.exception.ResourceNotFoundException;
import bg.duosoft.bpo.portal.dto.Section;
import bg.duosoft.bpo.portal.dto.ServiceGroup;
import bg.duosoft.bpo.portal.entity.PanelEntity;
import bg.duosoft.bpo.portal.mapper.PanelAndServicesMapper;
import bg.duosoft.bpo.portal.mapper.PanelMapper;
import bg.duosoft.bpo.portal.repository.PanelRepository;
import bg.duosoft.bpo.portal.repository.ServiceDefinitionRepository;
import bg.duosoft.bpo.portal.service.PanelService;
import jakarta.transaction.Transactional;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PanelServiceImpl extends CrudServiceBaseImpl<String, Section> implements PanelService {

    private final PanelRepository panelRepository;
    private final ServiceDefinitionRepository serviceDefinitionRepository;
    private final PanelMapper panelMapper;
    private final PanelAndServicesMapper panelAndServicesMapper;

    @Override
    public ServiceGroup getPanelAndServicesByPanelId(String panelId) {
        PanelEntity panel = this.panelRepository.findPanelById(panelId);

        if (Objects.isNull(panel)) {
            throw new ResourceNotFoundException();
        }

        return this.panelAndServicesMapper.toDto(panel, this.serviceDefinitionRepository.findAllServicesByPanelId(panelId));
    }

    @Override
    public Section getPanelAndPanelChildren(String panelId) {
        return this.panelMapper
                .toDto(this.panelRepository.findPanelById(panelId));
    }

    @Override
    protected PanelRepository getRepository() {
        return this.panelRepository;
    }

    @Override
    protected PanelMapper getMapper() {
        return this.panelMapper;
    }

    @Override
    protected Validator<Section> getValidator() {
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
