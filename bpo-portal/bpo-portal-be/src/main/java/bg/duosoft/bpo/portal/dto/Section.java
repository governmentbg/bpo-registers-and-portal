package bg.duosoft.bpo.portal.dto;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
public class Section implements BaseDTO<String> {
    private String id;
    private String labelBg;
    private String labelEn;
    private String descriptionBg;
    private String descriptionEn;
    private List<SectionChild> childrenPanels;
}
