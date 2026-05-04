package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpPatentSummaryDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPatentSummary;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:24
 */
@Mapper(componentModel = "spring", uses = {IpPatentSummaryIdMapper.class})
public abstract class IpPatentSummaryMapper extends BaseObjectMapper<EIpPatentSummary, IpPatentSummaryDTO> {

}
