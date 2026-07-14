package bg.duosoft.bpo.registers.mapper.ipobject.history.agent;

import bg.duosoft.bpo.registers.dto.ipobject.history.agent.AgentHistoryRepresentativeDataDTO;
import bg.duosoft.bpo.publik.core.enums.CivilIdType;
import bg.duosoft.bpo.registers.nonentity.history.agent.HistoryRepresentativeDataType;
import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
        AgentHistoryNameAddressMapper.class,
        AgentHistoryMapperHelper.class
})
public abstract class AgentHistoryRepresentativeDataMapper {

    @Mapping(source = "representativeCode", target = "agentCode")
    @Mapping(source = "ipObjects", target = "agentSpeciality", qualifiedByName = "agentSpecialityMapping")
    @Mapping(source = "formattedNameAddress", target = "nameAddress")
    public abstract AgentHistoryRepresentativeDataDTO toDto(HistoryRepresentativeDataType e);

}
