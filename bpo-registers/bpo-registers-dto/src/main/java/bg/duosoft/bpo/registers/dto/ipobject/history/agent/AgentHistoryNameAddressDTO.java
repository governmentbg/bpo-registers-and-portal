package bg.duosoft.bpo.registers.dto.ipobject.history.agent;

import lombok.Data;

@Data
public class AgentHistoryNameAddressDTO {
    private String name;
    private String nameEn;
    private AgentHistoryAddressDTO address;

}
