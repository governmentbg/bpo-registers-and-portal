package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.CountryDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:36
 */
@Data
public class IpPersonDTO implements BaseDTO<Integer> {

    private Integer id;
    private String name;
    private CountryDTO nationalityCountryCode;
    private String telephone;
    private String email;
    private Integer portalUserNumber;
    private IpPersonAddressDTO address;
    private IpAgentDTO agent;
    private Integer legalType;
    private String firstName;
    private String secondName;
    private String lastName;
}
