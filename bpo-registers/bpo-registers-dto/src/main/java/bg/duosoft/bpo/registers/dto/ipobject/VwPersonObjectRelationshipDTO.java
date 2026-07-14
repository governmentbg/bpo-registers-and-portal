package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

@Data
public class VwPersonObjectRelationshipDTO implements BaseDTO<VwPersonObjectRelationshipIdDTO> {
    private VwPersonObjectRelationshipIdDTO id;
    private String objectTitle;
    private String objectTitleEn;
    private String roleDescription;
    private String roleDescriptionEn;
    private String personName;
}
