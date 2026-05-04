package bg.duosoft.bpo.registers.dto.recordal;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RecordalTypeDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.StatusMapDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 15:25
 */
@Data
public class RecordalDTO implements BaseDTO<String> {

    private String id;
    private String recordalNumber;
    private RecordalTypeDTO recordalType;
    private RecordalDTO parentRecordal;
    private LocalDate filingDate;
    private String registrationDate;
    private LocalDate invalidationDate;
    private StatusMapDTO status;
    private List<RecordalDetailDTO> details;
}
