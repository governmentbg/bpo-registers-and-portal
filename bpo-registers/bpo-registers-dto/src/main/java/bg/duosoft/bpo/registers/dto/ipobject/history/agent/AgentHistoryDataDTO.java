package bg.duosoft.bpo.registers.dto.ipobject.history.agent;

import lombok.Data;

@Data
public class AgentHistoryDataDTO {
    private AgentHistoryRepresentativeDataDTO agent;
    private AgentHistoryPartnershipDataDTO partnership;
}
