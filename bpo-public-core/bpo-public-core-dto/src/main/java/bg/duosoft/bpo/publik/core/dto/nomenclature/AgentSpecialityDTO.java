package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:41
 */
@Data
public class AgentSpecialityDTO implements BaseDTO<String> {

    private String id;
    private String name;
    private String shortName;
    private String nameEn;
    private String shortNameEn;
    private Integer ipasCode;
}
