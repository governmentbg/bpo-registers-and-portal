package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

@Data
public class IpPatentIpcClassDTO implements BaseDTO<IpPatentIpcClassId> {
    private IpPatentIpcClassId id;
    private Integer classOrder;
    public String getFullIpcClassId() {
        return id.getSectionCode() + id.getClassCode() + id.getSubclassCode() + " " + id.getGroupCode() + "/" + id.getSubgroupCode();
    }
}
