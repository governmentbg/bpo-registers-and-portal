package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PlantTaxonDTO;
import lombok.Data;

import java.time.LocalDate;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 17:07
 */
@Data
public class IpPlantDTO implements IpObjectBaseDTO, BaseDTO<String> {

    private String id;
    private Integer drawingsCount;
    private LocalDate effectiveDate;
    private String mainAbstract;
    private String enAbstract;
    private String features;
    private String stability;
    private String testing;
    private String approvedTitle;
    private String approvedEnTitle;
    private IpObjectDTO ipObject;
    private PlantTaxonDTO taxon;
}
