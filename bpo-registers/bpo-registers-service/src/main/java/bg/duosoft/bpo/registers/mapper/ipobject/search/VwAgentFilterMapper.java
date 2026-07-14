package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.registers.nonentity.filter.VwAgentFilter;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 11.11.2024
 * Time: 14:14
 */
@Mapper(componentModel = "spring")
public abstract class VwAgentFilterMapper {
    public abstract VwAgentFilter toFilter(bg.duosoft.bpo.registers.dto.filter.VwAgentFilter filter);
}
