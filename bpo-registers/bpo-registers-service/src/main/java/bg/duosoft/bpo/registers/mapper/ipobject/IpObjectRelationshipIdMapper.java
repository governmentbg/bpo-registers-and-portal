package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectRelationshipId;
import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectRelationshipId;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:01
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class IpObjectRelationshipIdMapper extends BaseObjectMapper<EIpObjectRelationshipId, IpObjectRelationshipId> {
}
