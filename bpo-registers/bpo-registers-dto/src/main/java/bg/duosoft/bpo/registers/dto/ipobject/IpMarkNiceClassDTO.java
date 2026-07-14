package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:06
 */
@Data
public class IpMarkNiceClassDTO implements BaseDTO<IpMarkNiceClassId> {

    private IpMarkNiceClassId id;

    private String specification;
}
