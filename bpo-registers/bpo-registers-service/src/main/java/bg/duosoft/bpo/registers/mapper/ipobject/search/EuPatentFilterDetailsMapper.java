package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.registers.dto.filter.EuPatentFilterDetailsDTO;
import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.02.2024
 * Time: 16:48
 */
@Mapper(componentModel = "spring")
public abstract class EuPatentFilterDetailsMapper extends PatentLikeFilterDetailsMapper<EuPatentFilterDetailsDTO> {

    @InheritConfiguration(name = "toIpObjectFilterInternal")
    public abstract IpObjectFilter toIpObjectFilter(EuPatentFilterDetailsDTO euPatentFilterDetailsDTO);
}
