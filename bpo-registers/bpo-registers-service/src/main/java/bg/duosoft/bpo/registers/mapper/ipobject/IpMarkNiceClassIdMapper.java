package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkNiceClassDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkNiceClassId;
import bg.duosoft.bpo.registers.entity.ipobject.EIpMarkNiceClass;
import bg.duosoft.bpo.registers.entity.ipobject.EIpMarkNiceClassId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:33
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class IpMarkNiceClassIdMapper extends BaseObjectMapper<EIpMarkNiceClassId, IpMarkNiceClassId> {

}
