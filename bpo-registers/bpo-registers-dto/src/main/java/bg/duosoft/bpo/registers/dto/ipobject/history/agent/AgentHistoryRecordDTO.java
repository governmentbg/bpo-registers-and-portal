package bg.duosoft.bpo.registers.dto.ipobject.history.agent;

import lombok.Data;

@Data
public class AgentHistoryRecordDTO {
    private AgentHistoryGroundDTO grounds;
    private AgentHistoryDataDTO oldData;
    private AgentHistoryDataDTO newData;
}
