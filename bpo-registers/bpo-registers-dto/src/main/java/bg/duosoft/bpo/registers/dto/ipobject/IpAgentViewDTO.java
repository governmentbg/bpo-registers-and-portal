package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IpAgentViewDTO implements BaseDTO<Integer> {
    private Integer id;
    private IpPersonDTO agent;
    private List<IpPersonDTO> agentRelations;
    private List<IpAgentHistoryDTO> agentHistory;
}
