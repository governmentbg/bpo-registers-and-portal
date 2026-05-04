package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectPublication;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectPublicationDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.PublicationSectionMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:08
 */
@Mapper(componentModel = "spring", uses = {
        PublicationSectionMapper.class
})
public abstract class IpObjectPublicationMapper extends BaseObjectMapper<EIpObjectPublication, IpObjectPublicationDTO> {
}
