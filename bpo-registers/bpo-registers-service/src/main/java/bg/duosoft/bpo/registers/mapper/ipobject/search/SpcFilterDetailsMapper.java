package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.dto.filter.SpcFilterDetailsDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.02.2024
 * Time: 16:48
 */
@Mapper(componentModel = "spring")
public abstract class SpcFilterDetailsMapper extends PatentLikeFilterDetailsMapper<SpcFilterDetailsDTO> {

    @InheritConfiguration(name = "toIpObjectFilterInternal")
    public abstract IpObjectFilter toIpObjectFilter(SpcFilterDetailsDTO spcFilterDetailsDTO);
}
