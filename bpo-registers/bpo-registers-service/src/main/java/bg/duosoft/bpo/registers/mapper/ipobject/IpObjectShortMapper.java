package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectShortDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectShort;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class IpObjectShortMapper extends BaseObjectMapper<EIpObjectShort, IpObjectShortDTO> {
}
