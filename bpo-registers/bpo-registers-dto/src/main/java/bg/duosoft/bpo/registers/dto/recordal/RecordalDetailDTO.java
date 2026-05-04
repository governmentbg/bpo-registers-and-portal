package bg.duosoft.bpo.registers.dto.recordal;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RecordalDetailTypeDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 15:32
 */
@Data
public class RecordalDetailDTO implements BaseDTO<Integer> {

    private Integer id;
    private RecordalDetailTypeDTO type;
    private String description;
    private Integer orderBy;
}
