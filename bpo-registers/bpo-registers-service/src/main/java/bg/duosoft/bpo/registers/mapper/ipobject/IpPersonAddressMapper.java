package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpPersonAddress;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonAddressDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.CountryMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:14
 */
@Mapper(componentModel = "spring", uses = {
        CountryMapper.class
})
public abstract class IpPersonAddressMapper extends BaseObjectMapper<EIpPersonAddress, IpPersonAddressDTO> {
}
