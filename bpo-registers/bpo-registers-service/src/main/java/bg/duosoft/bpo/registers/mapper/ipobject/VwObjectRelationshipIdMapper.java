package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.VwObjectRelationshipIdDTO;
import bg.duosoft.bpo.registers.entity.ipobject.VwObjectRelationshipId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class VwObjectRelationshipIdMapper extends BaseObjectMapper<VwObjectRelationshipId, VwObjectRelationshipIdDTO> {
}
