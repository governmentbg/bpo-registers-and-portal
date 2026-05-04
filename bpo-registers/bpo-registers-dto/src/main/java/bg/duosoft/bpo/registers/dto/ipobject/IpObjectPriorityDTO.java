package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.CountryDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PriorityTypeDTO;
import lombok.Data;

import java.time.LocalDate;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:27
 */
@Data
public class IpObjectPriorityDTO implements BaseDTO<Integer> {

    private Integer id;
    private String objectId;
    private CountryDTO country;
    private String priorityNumber;
    private LocalDate priorityDate;
    private String remarks;
    private Integer status;
    private PriorityTypeDTO priorityType;
    private Integer priorityOrder;
}
