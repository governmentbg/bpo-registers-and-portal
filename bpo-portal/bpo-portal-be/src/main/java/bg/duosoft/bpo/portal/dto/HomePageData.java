package bg.duosoft.bpo.portal.dto;

import lombok.Data;

import java.util.List;

@Data
public class HomePageData {
    private String id;
    private String descriptionBg;
    private String descriptionEn;
    private List<Section> sections;
}
