package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.common.AttachmentMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectAttachmentDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectAttachment;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {
        AttachmentMapper.class,
        IpObjectAttachmentIdMapper.class
})
public abstract class IpObjectAttachmentMapper extends BaseObjectMapper<EIpObjectAttachment, IpObjectAttachmentDTO> {
}
