package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.dto.filter.MarkFilterDetailsDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.02.2024
 * Time: 19:09
 */
@Mapper(componentModel = "spring")
public abstract class MarkFilterDetailsMapper extends MarkLikeFilterDetailsMapper<MarkFilterDetailsDTO> {

    @InheritConfiguration(name = "toIpObjectFilterInternal")
    @Mapping(target = "markKind", source = "markKind.id")
    public abstract IpObjectFilter toIpObjectFilter(MarkFilterDetailsDTO markLikeFilterDetailsDTO);
}
