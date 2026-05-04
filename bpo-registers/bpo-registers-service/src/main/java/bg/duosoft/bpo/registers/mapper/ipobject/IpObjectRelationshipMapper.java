package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectRelationship;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectRelationshipDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.RelationshipTypeMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:01
 */
@Mapper(componentModel = "spring", uses = {
        IpObjectRelationshipIdMapper.class,
        RelationshipTypeMapper.class
})
public abstract class IpObjectRelationshipMapper extends BaseObjectMapper<EIpObjectRelationship, IpObjectRelationshipDTO> {

    public abstract IpObjectRelationshipDTO toDto(EIpObjectRelationship eIpObjectRelationship);
}
