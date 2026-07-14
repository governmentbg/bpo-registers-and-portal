package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:15
 */
@Data
public class IpPatentSummaryDTO implements BaseDTO<IpPatentSummaryId> {

    private IpPatentSummaryId id;

    private String description;
}
