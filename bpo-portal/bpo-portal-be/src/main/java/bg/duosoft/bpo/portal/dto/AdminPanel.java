package bg.duosoft.bpo.portal.dto;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
public class AdminPanel implements BaseDTO<Integer> {
    private Integer id;
    private String title;
    private String titleEn;
    private List<String> accessRoles;
    private Integer position;
    private String url;
    private Integer parentId;
    private List<AdminPanel> children;
}
