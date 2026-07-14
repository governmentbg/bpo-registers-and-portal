package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpPatentSummaryDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpPatentSummaryId;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPatentSummary;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPatentSummaryId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:24
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class IpPatentSummaryIdMapper extends BaseObjectMapper<EIpPatentSummaryId, IpPatentSummaryId> {
}
