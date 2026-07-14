package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpPatentCpcClassId;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPatentCpcClassId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {

})
public abstract class IpPatentCpcClassIdMapper extends BaseObjectMapper<EIpPatentCpcClassId, IpPatentCpcClassId> {

}
