package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpPatentCitationDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPatentCitation;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:29
 */
@Mapper(componentModel = "spring", uses = {IpPatentCitationIdMapper.class})
public abstract class IpPatentCitationMapper extends BaseObjectMapper<EIpPatentCitation, IpPatentCitationDTO> {

}
