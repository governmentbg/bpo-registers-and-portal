package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.registers.dto.ipobject.history.agent.AgentHistoryRecordDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.AgentHistoryTypeDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IpAgentHistoryDTO implements BaseDTO<Integer> {
    private Integer id;
    private Integer personId;
    private AgentHistoryTypeDTO historyType;
    private LocalDateTime historyTimestamp;
    private AgentHistoryRecordDTO historyRecord;
    private String initiatingDoc;
}
