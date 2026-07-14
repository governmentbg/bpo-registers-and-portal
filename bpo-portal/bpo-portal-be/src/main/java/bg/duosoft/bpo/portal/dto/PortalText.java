package bg.duosoft.bpo.portal.dto;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PortalText implements BaseDTO<String> {
    private String id;
    private String descriptionBg;
    private String descriptionEn;
    private Integer dynamicContent;
    private LocalDateTime activeFrom;
    private LocalDateTime activeTo;
}
