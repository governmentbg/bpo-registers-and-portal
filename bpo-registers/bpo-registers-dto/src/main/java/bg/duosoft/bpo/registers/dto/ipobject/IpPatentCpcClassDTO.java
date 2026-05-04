package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

@Data
public class IpPatentCpcClassDTO implements BaseDTO<IpPatentCpcClassId> {
    private IpPatentCpcClassId id;
    private Integer classOrder;
}
