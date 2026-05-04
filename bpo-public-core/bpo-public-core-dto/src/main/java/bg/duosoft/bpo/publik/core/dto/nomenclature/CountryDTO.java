package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.04.2022
 * Time: 17:26
 */
@Data
public class CountryDTO implements BaseDTO<String> {

    private String id;
    private String name;
    private String nameEn;
}
