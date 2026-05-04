package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.common.service.mapper.IntegerToBooleanMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpReplicationTimeDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpReplicationTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, IpReplicationDetailMapper.class})
public abstract class IpReplicationTimeMapper extends BaseObjectMapper<EIpReplicationTime, IpReplicationTimeDTO> {
}
