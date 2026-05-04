package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectAttachmentId;
import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectAttachmentId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public abstract class IpObjectAttachmentIdMapper extends BaseObjectMapper<EIpObjectAttachmentId, IpObjectAttachmentId> {

}
