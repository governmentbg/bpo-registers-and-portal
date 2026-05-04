package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 15:29
 */
@Data
public class RecordalTypeDTO implements BaseDTO<String> {

    private String id;
    private String description;
    private String descriptionEn;
}
