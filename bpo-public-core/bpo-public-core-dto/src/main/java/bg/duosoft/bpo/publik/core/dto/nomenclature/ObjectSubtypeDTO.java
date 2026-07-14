package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:13
 */
@Data
public class ObjectSubtypeDTO implements BaseDTO<String> {
    private String id;
    private ObjectTypeDTO objectType;
    private String description;
    private String descriptionEn;
}
