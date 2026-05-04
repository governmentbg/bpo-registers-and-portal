package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpPatentIpcClassDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpPatentIpcClassId;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPatentIpcClass;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPatentIpcClassId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:29
 */
@Mapper(componentModel = "spring", uses = {

})
public abstract class IpPatentIpcClassIdMapper extends BaseObjectMapper<EIpPatentIpcClassId, IpPatentIpcClassId> {

}
