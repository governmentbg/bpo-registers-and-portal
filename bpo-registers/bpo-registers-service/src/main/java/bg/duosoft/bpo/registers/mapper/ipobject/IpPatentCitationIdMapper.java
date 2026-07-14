package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpPatentCitationId;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPatentCitationId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {

})
public abstract class IpPatentCitationIdMapper extends BaseObjectMapper<EIpPatentCitationId, IpPatentCitationId> {

}
