package bg.duosoft.bpo.registers.controller.v1.ipobject;

import bg.duosoft.bpo.registers.dto.ipobject.IpAgentHistoryDTO;
import bg.duosoft.bpo.registers.service.ipobject.IpAgentHistoryService;
import bg.duosoft.bpo.registers.util.swagger.Tags;
import bg.duosoft.bpo.registers.util.swagger.annotation.IpObjectGroup;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@IpObjectGroup
@RestController
@Api(tags = Tags.AGENT_HISTORY)
@RequestMapping(value = {"/api/v1/agent-history"})
@RequiredArgsConstructor
public class AgentHistoryController {

    private final IpAgentHistoryService ipAgentHistoryService;

    @GetMapping("/by-person-id/{personId}")
    public List<IpAgentHistoryDTO> getByAgentCode(@PathVariable Integer personId) {
        return ipAgentHistoryService.selectAllByPersonId(personId);
    }
}
