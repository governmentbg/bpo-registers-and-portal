package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.registers.dto.filter.CommonIpObjectFilterDetailsDTO;
import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CombinedFilterDetailsMapper extends CommonIpObjectFilterMapper {

    @InheritConfiguration(name = "toIpObjectFilterConfig")
    public abstract IpObjectFilter toIpObjectFilter(CommonIpObjectFilterDetailsDTO combinedFilterDetailsDTO);
}
