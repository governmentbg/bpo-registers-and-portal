package bg.duosoft.bpo.portal.mapper;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.portal.dto.PortalAttachment;
import bg.duosoft.bpo.portal.entity.PortalAttachmentEntity;
import bg.duosoft.bpo.publik.core.mapper.common.AttachmentMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AttachmentMapper.class})
public abstract class PortalAttachmentMapper extends BaseObjectMapper<PortalAttachmentEntity, PortalAttachment> {
}
