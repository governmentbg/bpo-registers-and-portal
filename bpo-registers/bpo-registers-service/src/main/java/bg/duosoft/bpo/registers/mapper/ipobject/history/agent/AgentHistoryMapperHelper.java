package bg.duosoft.bpo.registers.mapper.ipobject.history.agent;

import bg.duosoft.bpo.publik.core.dto.nomenclature.AgentSpecialityDTO;
import bg.duosoft.bpo.publik.core.enums.AgentSpecialityType;
import bg.duosoft.bpo.registers.nonentity.history.agent.IpObjectsType;
import bg.duosoft.bpo.publik.core.service.nomenclature.AgentSpecialityService;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Mapper(componentModel = "spring")
public class AgentHistoryMapperHelper {

    @Autowired
    private AgentSpecialityService agentSpecialityService;

    @Named("agentSpecialityMapping")
    public AgentSpecialityDTO agentSpecialityMapping(IpObjectsType e) {
        if (Objects.isNull(e)) {
            return null;
        }

        AgentSpecialityType agentSpecialityType = AgentSpecialityType.selectByName(e.value());
        if (Objects.isNull(agentSpecialityType)) {
            return null;

        }

        return agentSpecialityService.getById(agentSpecialityType.code());
    }
}

