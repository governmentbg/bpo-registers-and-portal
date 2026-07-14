package bg.duosoft.bpo.portal.mapper;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.portal.dto.PortalAttachmentShort;
import bg.duosoft.bpo.portal.entity.PortalAttachmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PortalAttachmentShortMapper extends BaseObjectMapper<PortalAttachmentEntity, PortalAttachmentShort> {
}
