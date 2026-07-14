package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:16
 */
@Data
public class TmViewStatusMapDTO implements BaseDTO<String> {

    private String id;
    private String tmviewListCode;
    private String tmviewDetailedCode;
    private String tmviewDetailedCodeBg;
    private Integer showPersonMissingPublication;
}
