package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkAttachmentDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkAttachmentId;
import bg.duosoft.bpo.registers.entity.ipobject.EIpMarkAttachment;
import bg.duosoft.bpo.registers.entity.ipobject.EIpMarkAttachmentId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:29
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class IpMarkAttachmentIdMapper extends BaseObjectMapper<EIpMarkAttachmentId, IpMarkAttachmentId> {

}
