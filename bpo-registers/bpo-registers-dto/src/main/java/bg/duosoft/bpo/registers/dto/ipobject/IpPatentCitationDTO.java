package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 02.02.2024
 * Time: 14:30
 */
@Data
public class IpPatentCitationDTO implements BaseDTO<IpPatentCitationId> {
    private IpPatentCitationId id;
    private String refDescription;
    private String refClaims;

}
