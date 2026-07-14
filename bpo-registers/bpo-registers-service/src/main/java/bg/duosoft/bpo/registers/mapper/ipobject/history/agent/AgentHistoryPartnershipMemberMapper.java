package bg.duosoft.bpo.registers.mapper.ipobject.history.agent;

import bg.duosoft.bpo.registers.dto.ipobject.history.agent.AgentHistoryPartnershipMemberDTO;
import bg.duosoft.bpo.registers.nonentity.history.agent.PartnershipMemberType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class AgentHistoryPartnershipMemberMapper {

    @Mapping(source = "partnershipMemberName", target = "agentName")
    @Mapping(source = "partnershipMemberCode", target = "agentCode")
    public abstract AgentHistoryPartnershipMemberDTO toDto(PartnershipMemberType e);

}
