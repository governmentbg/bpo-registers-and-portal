package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpMarkAttachmentViennaClass;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkAttachmentViennaClassDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:31
 */
@Mapper(componentModel = "spring", uses = {
        IpMarkAttachmentViennaClassIdMapper.class
})
public abstract class IpMarkAttachmentViennaClassMapper extends BaseObjectMapper<EIpMarkAttachmentViennaClass, IpMarkAttachmentViennaClassDTO> {
    public abstract IpMarkAttachmentViennaClassDTO toDto(EIpMarkAttachmentViennaClass eIpMarkAttachmentViennaClass);
}
