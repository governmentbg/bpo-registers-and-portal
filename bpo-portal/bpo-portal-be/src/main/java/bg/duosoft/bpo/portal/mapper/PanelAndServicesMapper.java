package bg.duosoft.bpo.portal.mapper;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.portal.dto.ServiceGroup;
import bg.duosoft.bpo.portal.entity.PanelEntity;
import bg.duosoft.bpo.portal.entity.ServiceDefinitionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ServiceDefinitionMapper.class})
public abstract class PanelAndServicesMapper extends BaseObjectMapper<PanelEntity, ServiceGroup> {

    @Mapping(target = "childrenServices", source = "serviceDefinitionEntityList")
    public abstract ServiceGroup toDto(PanelEntity entity, List<ServiceDefinitionEntity> serviceDefinitionEntityList);

}
