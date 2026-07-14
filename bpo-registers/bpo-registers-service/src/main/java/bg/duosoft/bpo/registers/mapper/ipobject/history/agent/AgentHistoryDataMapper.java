package bg.duosoft.bpo.registers.mapper.ipobject.history.agent;

import bg.duosoft.bpo.registers.dto.ipobject.history.agent.AgentHistoryDataDTO;
import bg.duosoft.bpo.registers.nonentity.history.agent.HistoryDataType;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {
        AgentHistoryRepresentativeDataMapper.class,
        AgentHistoryPartnershipDataMapper.class
})
public abstract class AgentHistoryDataMapper {

    @Mapping(source = "historyRepresentativeData", target = "agent")
    @Mapping(source = "historyPartnershipData", target = "partnership")
    public abstract AgentHistoryDataDTO toDto(HistoryDataType e);

}
