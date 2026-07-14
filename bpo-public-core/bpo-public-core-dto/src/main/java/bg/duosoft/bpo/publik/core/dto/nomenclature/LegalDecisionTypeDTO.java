package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class LegalDecisionTypeDTO implements BaseDTO<Integer> {
    private Integer id;
    private String name;
    private String nameEn;
}
