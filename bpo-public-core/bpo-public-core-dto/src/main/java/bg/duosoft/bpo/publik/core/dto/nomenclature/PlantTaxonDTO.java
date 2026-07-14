package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 05.05.2022
 * Time: 13:21
 */
@Data
public class PlantTaxonDTO implements BaseDTO<Integer> {
    private Integer id;
    private String taxonCode;
    private String commonClassifyBg;
    private String commonClassifyEn;
    private String latinClassify;
}
