package bg.duosoft.bpo.portal.mapper;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.portal.dto.PortalText;
import bg.duosoft.bpo.portal.entity.PortalTextEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PortalTextMapper extends BaseObjectMapper<PortalTextEntity, PortalText> {
}
