package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.registers.dto.filter.AgentFilter;
import bg.duosoft.bpo.registers.nonentity.filter.EIpAgentDataFilter;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.02.2024
 * Time: 19:09
 */
@Mapper(componentModel = "spring")
public abstract class AgentFilterDetailsMapper {
    public abstract EIpAgentDataFilter toIpAgentFilter( AgentFilter eIpAgentDataFilter);
}
