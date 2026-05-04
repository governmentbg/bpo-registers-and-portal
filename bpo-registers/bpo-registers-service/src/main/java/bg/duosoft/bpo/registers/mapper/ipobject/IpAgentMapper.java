package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpAgent;
import bg.duosoft.bpo.registers.dto.ipobject.IpAgentDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.AgentSpecialityMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.AgentStatusMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.CountryMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.RepresentativeTypeMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:16
 */
@Mapper(componentModel = "spring", uses = {
        RepresentativeTypeMapper.class,
        AgentSpecialityMapper.class,
        CountryMapper.class,
        IpAgentAddressMapper.class,
        AgentStatusMapper.class
})
public abstract class IpAgentMapper extends BaseObjectMapper<EIpAgent, IpAgentDTO> {
    @AfterMapping
    protected void afterMapping(IpAgentDTO source, @MappingTarget EIpAgent target) {
        if (target.getAddress() != null) {
            target.getAddress().setAgent(target);
        }
    }

}
