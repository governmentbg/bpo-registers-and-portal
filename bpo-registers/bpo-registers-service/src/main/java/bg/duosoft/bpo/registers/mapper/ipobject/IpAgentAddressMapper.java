package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpAgentAddress;
import bg.duosoft.bpo.registers.dto.ipobject.IpAgentAddressDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:19
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class IpAgentAddressMapper extends BaseObjectMapper<EIpAgentAddress, IpAgentAddressDTO> {
}
