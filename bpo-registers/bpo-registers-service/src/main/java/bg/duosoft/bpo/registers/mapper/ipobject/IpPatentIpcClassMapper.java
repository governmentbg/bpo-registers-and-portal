package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpPatentIpcClassDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPatentIpcClass;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:29
 */
@Mapper(componentModel = "spring", uses = {IpPatentIpcClassIdMapper.class})
public abstract class IpPatentIpcClassMapper extends BaseObjectMapper<EIpPatentIpcClass, IpPatentIpcClassDTO> {

}
