package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpObject;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.ObjectSubtypeMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.ObjectTypeMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.StatusMapMapper;
import bg.duosoft.bpo.registers.mapper.recordal.RecordalMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 12:52
 */
@Mapper(componentModel = "spring", uses = {
        ObjectTypeMapper.class,
        ObjectSubtypeMapper.class,
        StatusMapMapper.class,
        IpObjectRelationshipMapper.class,
        IpObjectPriorityMapper.class,
        IpObjectPublicationMapper.class,
        IpPersonToIpObjectMapper.class,
        RecordalMapper.class,
        IpObjectAttachmentMapper.class
})
public abstract class IpObjectMapper extends BaseObjectMapper<EIpObject, IpObjectDTO> {

}
