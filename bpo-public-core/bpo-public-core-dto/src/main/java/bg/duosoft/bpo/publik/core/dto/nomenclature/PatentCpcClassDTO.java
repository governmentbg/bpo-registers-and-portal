package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

@Data
public class PatentCpcClassDTO implements BaseDTO<PatentCpcClassId> {
    private PatentCpcClassId id;
    private String name;
    private String nameEn;
}