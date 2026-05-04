package bg.duosoft.bpo.portal.dto;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

@Data
public class SectionChild implements BaseDTO<String> {
    private String id;
    private String titleBg;
    private String titleEn;
    private String descriptionBg;
    private String descriptionEn;
    private String action;
    private String actionTarget;
    private String img;
}
