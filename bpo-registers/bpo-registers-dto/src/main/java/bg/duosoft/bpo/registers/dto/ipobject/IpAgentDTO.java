package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.AgentSpecialityDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.AgentStatusDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.CountryDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RepresentativeTypeDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:39
 */
@Data
public class IpAgentDTO implements BaseDTO<Integer> {

    private Integer id;
    private String agentCode;
    private RepresentativeTypeDTO representativeType;
    private AgentSpecialityDTO agentSpeciality;
    private String nameEn;
    private String firstNameEn;
    private String secondNameEn;
    private String lastNameEn;
    private CountryDTO qualifCountryCode;
    private String speciality;
    private String specialityEn;
    private AgentStatusDTO agentStatus;
    private String representPersons;
    private IpAgentAddressDTO address;
}
