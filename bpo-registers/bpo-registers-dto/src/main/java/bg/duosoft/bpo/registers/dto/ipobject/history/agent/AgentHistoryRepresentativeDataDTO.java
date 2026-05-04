package bg.duosoft.bpo.registers.dto.ipobject.history.agent;

import bg.duosoft.bpo.publik.core.dto.nomenclature.AgentSpecialityDTO;
import lombok.Data;

@Data
public class AgentHistoryRepresentativeDataDTO {
    private String agentCode;
    private AgentHistoryNameAddressDTO nameAddress;
    private String diplomaSpeciality;
    private String diplomaSpecialityEn;
    private AgentSpecialityDTO agentSpeciality;
    private String qualificationCountryCode;
    private String livingAddress;
}
