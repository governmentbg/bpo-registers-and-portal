package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.registers.dto.filter.PersonFilter;
import bg.duosoft.bpo.registers.nonentity.filter.EIpPersonFilter;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 07.11.2024
 * Time: 15:35
 */
@Mapper(componentModel = "spring")
public abstract class PersonFilterMapper {
    public abstract EIpPersonFilter toEntity(PersonFilter filter);
}
