package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

@Data
public class PatentStatusMapDTO implements BaseDTO<String> {

    private String id;
    private String foStatusCode;
}
