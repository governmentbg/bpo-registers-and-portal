package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonToIpObjectId;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPersonToIpObjectId;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:09
 */
@Mapper(componentModel = "spring", uses = {
})
public abstract class IpPersonToIpObjectIdMapper extends BaseObjectMapper<EIpPersonToIpObjectId, IpPersonToIpObjectId> {

}
