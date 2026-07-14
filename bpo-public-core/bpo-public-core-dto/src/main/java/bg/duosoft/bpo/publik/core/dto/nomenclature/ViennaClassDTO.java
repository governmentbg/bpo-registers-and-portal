package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 13.02.2024
 * Time: 13:28
 */
@Data
public class ViennaClassDTO implements BaseDTO<String> {

    private String id;
    private String name;
    private Boolean titleCategoryFlag;
}
