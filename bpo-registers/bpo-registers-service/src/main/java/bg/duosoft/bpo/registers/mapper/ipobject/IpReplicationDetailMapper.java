package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpReplicationDetailDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpReplicationDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class IpReplicationDetailMapper extends BaseObjectMapper<EIpReplicationDetail, IpReplicationDetailDTO> {
}
