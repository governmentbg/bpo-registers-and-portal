package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkAttachmentViennaClassDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkAttachmentViennaClassId;
import bg.duosoft.bpo.registers.entity.ipobject.EIpMarkAttachmentViennaClass;
import bg.duosoft.bpo.registers.entity.ipobject.EIpMarkAttachmentViennaClassId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:31
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class IpMarkAttachmentViennaClassIdMapper extends BaseObjectMapper<EIpMarkAttachmentViennaClassId, IpMarkAttachmentViennaClassId> {

}
