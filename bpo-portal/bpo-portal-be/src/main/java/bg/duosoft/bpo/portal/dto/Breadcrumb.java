package bg.duosoft.bpo.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Breadcrumb {
    private String labelBg;
    private String labelEn;
}
