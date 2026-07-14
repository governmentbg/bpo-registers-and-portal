package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.VwPersonObjectRelationshipIdDTO;
import bg.duosoft.bpo.registers.entity.ipobject.VwPersonObjectRelationshipId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class VwPersonObjectRelationshipIdMapper extends BaseObjectMapper<VwPersonObjectRelationshipId, VwPersonObjectRelationshipIdDTO> {
}
