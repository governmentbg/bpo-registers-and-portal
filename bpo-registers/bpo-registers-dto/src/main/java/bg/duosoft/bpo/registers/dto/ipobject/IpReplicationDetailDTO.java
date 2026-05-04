package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

@Data
public class IpReplicationDetailDTO implements BaseDTO<Integer> {
    private Integer id;
    private String objectId;
    private Integer replicationTimeId;
    private String operationCode;
}
