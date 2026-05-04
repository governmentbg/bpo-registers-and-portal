package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class IpReplicationTimeDTO implements BaseDTO<Integer> {
    private Integer id;
    private LocalDateTime dateStart;
    private List<IpReplicationDetailDTO> details;
    private String replicatorType;
    private Integer modifiedRows;
    private Boolean xmlGenerated;
    private Boolean indexed;
    private LocalDateTime dateEnd;
    private Boolean exported;
}
