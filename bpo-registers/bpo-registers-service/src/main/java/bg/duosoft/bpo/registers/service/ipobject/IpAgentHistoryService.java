package bg.duosoft.bpo.registers.service.ipobject;

import bg.duosoft.bpo.registers.dto.ipobject.IpAgentHistoryDTO;

import java.util.List;

public interface IpAgentHistoryService {
    List<IpAgentHistoryDTO> selectAllByPersonId(Integer personId);
}
