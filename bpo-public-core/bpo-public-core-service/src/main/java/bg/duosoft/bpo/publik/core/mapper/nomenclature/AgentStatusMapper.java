package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.EAgentStatus;
import bg.duosoft.bpo.publik.core.dto.nomenclature.AgentStatusDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public abstract class AgentStatusMapper extends BaseObjectMapper<EAgentStatus, AgentStatusDTO> {
}
