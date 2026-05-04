package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.CountryDTO;
import lombok.Data;

import java.time.LocalDate;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:19
 */
@Data
public class IpPatentPctDTO implements BaseDTO<String> {

    private String id;
    private String pctApplicationId;
    private LocalDate pctApplicationDate;
    private CountryDTO pctPublicationCountry;
    private String pctPublicationId;
    private LocalDate pctPublicationDate;
    private Integer pctPhase;
}
