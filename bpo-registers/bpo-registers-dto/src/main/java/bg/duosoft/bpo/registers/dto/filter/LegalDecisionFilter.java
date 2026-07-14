package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.BaseFilterDTO;
import bg.duosoft.bpo.common.dto.filter.SearchOperatorType;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LegalDecisionGroundTypeDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LegalDecisionTypeDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class LegalDecisionFilter extends BaseFilterDTO {

    private String objectId;
    private Integer documentNumber;
    private FromToFilterDetailsDTO<LocalDate> documentDate;
    private String objectType;
    private LegalDecisionTypeDTO documentType;
    private List<LegalDecisionGroundTypeDTO> legalGroundTypes;
    private SearchOperatorType legalGroundTypesOperatorType;

}
