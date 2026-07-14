package bg.duosoft.bpo.portal.mapper;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.portal.dto.ServiceDefinition;
import bg.duosoft.bpo.portal.entity.ServiceDefinitionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ServiceDefinitionMapper extends BaseObjectMapper<ServiceDefinitionEntity, ServiceDefinition> {

    @Override
    @Mapping(target = "titleBg", source = "labelBg")
    @Mapping(target = "titleEn", source = "labelEn")
    @Mapping(target = "actionNew", source = "newUrl")
    @Mapping(target = "actionView", source = "viewUrl")
    public abstract ServiceDefinition toDto(ServiceDefinitionEntity serviceDefinitionEntity);
}
