package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.VwObjectRelationshipDTO;
import bg.duosoft.bpo.registers.entity.ipobject.VwObjectRelationship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        VwObjectRelationshipIdMapper.class
})
public abstract class VwObjectRelationshipMapper extends BaseObjectMapper<VwObjectRelationship, VwObjectRelationshipDTO> {

    public abstract VwObjectRelationshipDTO toDto(VwObjectRelationship vwObjectRelationship);
}
