package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.AgentHistoryTypeDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EAgentHistoryType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AgentHistoryTypeMapper extends BaseObjectMapper<EAgentHistoryType, AgentHistoryTypeDTO> {
}
