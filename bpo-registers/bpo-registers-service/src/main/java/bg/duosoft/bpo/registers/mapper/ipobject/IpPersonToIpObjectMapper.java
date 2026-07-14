package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpPersonToIpObject;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonToIpObjectDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.PersonRoleMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.RepresentativeTypeMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:09
 */
@Mapper(componentModel = "spring", uses = {
        IpPersonMapper.class,
        PersonRoleMapper.class,
        RepresentativeTypeMapper.class,
        IpPersonToIpObjectIdMapper.class
})
public abstract class IpPersonToIpObjectMapper extends BaseObjectMapper<EIpPersonToIpObject, IpPersonToIpObjectDTO> {
}
