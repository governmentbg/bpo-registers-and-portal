package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectPriority;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectPriorityDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.CountryMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.PriorityTypeMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:06
 */
@Mapper(componentModel = "spring", uses = {
        CountryMapper.class,
        PriorityTypeMapper.class
})
public abstract class IpObjectPriorityMapper extends BaseObjectMapper<EIpObjectPriority, IpObjectPriorityDTO> {
}
