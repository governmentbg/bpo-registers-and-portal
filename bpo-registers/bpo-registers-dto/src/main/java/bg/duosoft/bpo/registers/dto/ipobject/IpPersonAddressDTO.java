package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.CountryDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:37
 */
@Data
public class IpPersonAddressDTO implements BaseDTO<Integer> {

    private Integer id;
    private String addressStreet;
    private String cityName;
    private CountryDTO residenceCountryCode;
    private String stateName;
    private String zipCode;
}
