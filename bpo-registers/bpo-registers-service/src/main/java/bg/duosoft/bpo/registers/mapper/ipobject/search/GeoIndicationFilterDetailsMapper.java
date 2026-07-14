package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.registers.dto.filter.GeoIndicationFilterDetailsDTO;
import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.02.2024
 * Time: 14:49
 */
@Mapper(componentModel = "spring")
public abstract class GeoIndicationFilterDetailsMapper extends MarkLikeFilterDetailsMapper<GeoIndicationFilterDetailsDTO> {

    @InheritConfiguration(name = "toIpObjectFilterInternal")
    public abstract IpObjectFilter toIpObjectFilter(GeoIndicationFilterDetailsDTO geoIndicationFilterDetailsDTO);
}
