package bg.duosoft.bpo.portal.dto;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

@Data
public class PortalAttachmentShort implements BaseDTO<String> {
    private String id;
    private String labelBg;
    private String labelEn;
    private String image;
    private String descriptionBg;
    private String descriptionEn;
    private Integer position;
    private Integer visibleFlag;
}
