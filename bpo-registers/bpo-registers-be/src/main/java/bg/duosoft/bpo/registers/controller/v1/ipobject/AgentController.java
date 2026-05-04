package bg.duosoft.bpo.registers.controller.v1.ipobject;

import bg.duosoft.bpo.common.util.exception.ResourceNotFoundException;
import bg.duosoft.bpo.registers.dto.filter.AgentFilter;
import bg.duosoft.bpo.registers.dto.ipobject.IpAgentDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpAgentHistoryDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpAgentViewDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonDTO;
import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import bg.duosoft.bpo.registers.service.ipobject.IpAgentHistoryService;
import bg.duosoft.bpo.registers.service.ipobject.IpAgentService;
import bg.duosoft.bpo.registers.util.swagger.Tags;
import bg.duosoft.bpo.registers.util.swagger.annotation.IpObjectGroup;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@IpObjectGroup
@RestController
@Api(tags = Tags.AGENTS)
@RequestMapping(value = {"/api/v1/agents"})
@RequiredArgsConstructor
public class AgentController {

    private final IpAgentService ipAgentService;
    private final IpAgentHistoryService ipAgentHistoryService;

    @PostMapping
    public Page<IpPersonDTO> search(@RequestBody AgentFilter filter) {
        return ipAgentService.filterAgents(filter);
    }

    @GetMapping("/by-agent-code/{agentCode}")
    public IpPersonDTO getByAgentCode(@PathVariable String agentCode) {
        return ipAgentService.getByAgentCode(agentCode);
    }

    @GetMapping("/relations/by-agent-code/{agentCode}")
    public List<IpPersonDTO> getRelationsByAgentCode(@PathVariable String agentCode) {
        return ipAgentService.getAgentRelationsByAgentCode(agentCode);
    }

    @GetMapping("/view/by-agent-code/{agentCode}")
    public IpAgentViewDTO getViewByAgentCode(@PathVariable String agentCode, @RequestParam("representativeTypes") List<RepresentativeType> representativeTypes) {
        IpPersonDTO agent = ipAgentService.getByAgentCodeAndTypeIn(agentCode, representativeTypes.stream().map(RepresentativeType::code).collect(Collectors.toList()));
        if (Objects.isNull(agent)) {
            throw new ResourceNotFoundException();
        }

        List<IpPersonDTO> agentRelations = ipAgentService.getAgentRelationsByAgentId(agent.getId(), agent.getAgent().getRepresentativeType().getId()).stream().sorted(Comparator.comparing(o -> o.getAgent().getAgentCode())).toList();
        List<IpAgentHistoryDTO> agentHistory = ipAgentHistoryService.selectAllByPersonId(agent.getId()).stream().sorted(Comparator.comparing(IpAgentHistoryDTO::getHistoryTimestamp).reversed()).toList();

        return IpAgentViewDTO.builder().id(agent.getId()).agent(agent).agentRelations(agentRelations).agentHistory(agentHistory).build();
    }

    @GetMapping("/cities")
    public List<String> filterAgentCities(@RequestParam("language") String language, @RequestParam("cityName") String cityName) {
        return ipAgentService.filterAgentCities(language, cityName);
    }

    @GetMapping("/specialities")
    public List<String> filterAgentSpecialities(@RequestParam("language") String language, @RequestParam("speciality") String speciality) {
        return ipAgentService.filterAgentSpecialities(language, speciality);
    }

    @GetMapping("/base-details")
    public List<IpPersonDTO> filterAgents(@RequestParam String nameOrAgentCode) {
        return ipAgentService.filterAgentsByNameOrAgentCode(nameOrAgentCode);
    }

    @GetMapping("/batch")
    public List<IpPersonDTO> getBatch(@RequestParam Long[] ids) {
        return ipAgentService.getBatchOfAgents(ids);
    }
}
