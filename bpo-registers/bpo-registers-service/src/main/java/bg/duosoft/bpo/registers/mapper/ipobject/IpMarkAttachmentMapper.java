package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.common.AttachmentMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkAttachmentDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpMarkAttachment;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:29
 */
@Mapper(componentModel = "spring", uses = {
        AttachmentMapper.class,
        IpMarkAttachmentViennaClassMapper.class,
        IpMarkAttachmentIdMapper.class
})
public abstract class IpMarkAttachmentMapper extends BaseObjectMapper<EIpMarkAttachment, IpMarkAttachmentDTO> {

}
