package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpPatent;
import bg.duosoft.bpo.registers.dto.ipobject.IpPatentDTO;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:32
 */
@Mapper(componentModel = "spring", uses = {
        IpObjectMapper.class,
        IpPatentSummaryMapper.class,
        IpPatentPctMapper.class,
        IpPatentIpcClassMapper.class,
        IpPatentCpcClassMapper.class,
        IpPatentCitationMapper.class
})
public abstract class IpPatentMapper extends IpObjectBaseMapper<EIpPatent, IpPatentDTO> {
}
