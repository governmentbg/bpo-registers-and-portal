package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:43
 */
@Data
public class IpAgentAddressDTO implements BaseDTO<Integer> {

    private Integer id;
    private String cityNameEn;
    private String addressStreetEn;
    private String website;
    private String addressStreetCa;
    private String addressStreetCaEn;
    private String zipCodeCa;
    private String phoneCa;
    private String emailCa;
    private String faxCa;
    private String cityNameCa;
    private String cityNameCaEn;
}
