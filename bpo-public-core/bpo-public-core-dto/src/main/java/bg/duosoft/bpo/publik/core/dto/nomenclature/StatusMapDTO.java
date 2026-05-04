package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:15
 */
@Data
public class StatusMapDTO implements BaseDTO<String> {

    private String id;
    private String backofficeStatusName;
    private Integer forDeletion;
    private String bpoOnlineStatus;
    private String bpoOnlineStatusEn;
    private Integer showExpirationDate;
    private String processType;
    private TmViewStatusMapDTO tmViewStatusMap;
    private EpoPatentStatusMapDTO epoPatentStatusMap;
    private DsViewStratusMapDTO dsViewStatusMap;
    private PatentStatusMapDTO patentStatusMap;
}
