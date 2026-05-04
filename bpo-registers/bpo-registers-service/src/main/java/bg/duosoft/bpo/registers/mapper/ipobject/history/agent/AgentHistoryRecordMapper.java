package bg.duosoft.bpo.registers.mapper.ipobject.history.agent;

import bg.duosoft.bpo.registers.dto.ipobject.history.agent.AgentHistoryRecordDTO;
import bg.duosoft.bpo.registers.nonentity.history.agent.HistoryRecordDataType;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {AgentHistoryDataMapper.class})
public abstract class AgentHistoryRecordMapper {

    @Mapping(source = "historyGrounds.historyGroundCode", target = "grounds.historyGroundCode")
    @Mapping(source = "historyGrounds.historyGroundDescription", target = "grounds.historyGroundDescription")
    @Mapping(source = "historyNewData", target = "newData")
    @Mapping(source = "historyOldData", target = "oldData")
    public abstract AgentHistoryRecordDTO toDto(HistoryRecordDataType e);

}
