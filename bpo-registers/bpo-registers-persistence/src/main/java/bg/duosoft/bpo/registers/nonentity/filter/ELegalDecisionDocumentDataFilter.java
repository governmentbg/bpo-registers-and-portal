package bg.duosoft.bpo.registers.nonentity.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ELegalDecisionDocumentDataFilter extends EExtraDataFilter {

    private String objectId;
    private Integer documentNumber;
    private LocalDate documentDateFrom;
    private LocalDate documentDateTo;
    private String objectType;
    private Integer documentType;
    private List<Integer> legalGroundTypes;
    private ESearchOperatorType legalGroundTypesOperatorType;
}
