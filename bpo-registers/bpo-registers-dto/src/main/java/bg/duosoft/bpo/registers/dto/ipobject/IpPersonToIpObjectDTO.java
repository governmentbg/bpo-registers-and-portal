package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PersonRoleDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RepresentativeTypeDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:45
 */
@Data
public class IpPersonToIpObjectDTO implements BaseDTO<IpPersonToIpObjectId> {

    private IpPersonToIpObjectId id;

    private Integer personOrder;
    private IpPersonDTO person;
    private PersonRoleDTO role;
    private RepresentativeTypeDTO representativeType;
}
