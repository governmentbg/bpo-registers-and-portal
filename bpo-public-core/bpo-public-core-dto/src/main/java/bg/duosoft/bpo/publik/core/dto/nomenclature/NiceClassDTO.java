package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

@Data
public class NiceClassDTO implements BaseDTO<Integer> {
    private Integer id;
    private String heading;
}
