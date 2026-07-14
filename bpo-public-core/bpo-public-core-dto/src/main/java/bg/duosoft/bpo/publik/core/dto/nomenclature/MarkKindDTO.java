package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:04
 */
@Data
public class MarkKindDTO implements BaseDTO<String> {

    private String id;
    private String description;
    private String descriptionEn;
    private String tmviewMarkFeature;
}
