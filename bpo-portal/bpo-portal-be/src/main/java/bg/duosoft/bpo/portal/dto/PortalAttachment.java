package bg.duosoft.bpo.portal.dto;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.common.AttachmentDTO;
import lombok.Data;

@Data
public class PortalAttachment implements BaseDTO<String> {
    private String id;
    private String labelBg;
    private String labelEn;
    private String image;
    private String descriptionBg;
    private String descriptionEn;
    private Integer position;
    private Integer visibleFlag;
    private AttachmentDTO attachment;
}
