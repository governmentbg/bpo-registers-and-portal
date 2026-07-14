package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpPerson;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.CountryMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:11
 */
@Mapper(componentModel = "spring", uses = {
        CountryMapper.class,
        IpPersonAddressMapper.class,
        IpAgentMapper.class
})
public abstract class IpPersonMapper extends BaseObjectMapper<EIpPerson, IpPersonDTO> {
    @AfterMapping
    protected void afterMapping(IpPersonDTO source, @MappingTarget EIpPerson target) {
        if (target.getAddress() != null) {
            target.getAddress().setPerson(target);
        }
        if (target.getAgent() != null) {
            target.getAgent().setPerson(target);
        }
    }
}
