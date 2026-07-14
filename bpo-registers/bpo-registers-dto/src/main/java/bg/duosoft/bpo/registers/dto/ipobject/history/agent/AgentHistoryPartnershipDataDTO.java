package bg.duosoft.bpo.registers.dto.ipobject.history.agent;

import bg.duosoft.bpo.publik.core.dto.nomenclature.AgentSpecialityDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RepresentativeTypeDTO;
import bg.duosoft.bpo.publik.core.enums.PartnershipMemberKind;
import lombok.Data;

import java.util.List;

@Data
public class AgentHistoryPartnershipDataDTO {
    private String agentCode;
    private RepresentativeTypeDTO representativeType;
    private AgentHistoryNameAddressDTO nameAddress;
    private String nationalNumber;
    private String partnershipRepresentativeName;
    private PartnershipMemberKind partnershipRepresentativeKind;
    private AgentSpecialityDTO agentSpeciality;
    private String oriCountryCode;
    private String oriRepresentativesNames;
    private List<AgentHistoryPartnershipMemberDTO> partnershipMembers;
}
