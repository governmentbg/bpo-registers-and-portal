package bg.duosoft.bpo.registers.dto.legaldecision;

import bg.duosoft.bpo.publik.core.dto.nomenclature.LegalDecisionGroundTypeDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LegalDecisionTypeDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ObjectTypeDTO;
import bg.duosoft.bpo.common.dto.BaseDTO;

import bg.duosoft.bpo.publik.core.dto.common.AttachmentDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class LegalDecisionDocumentDTO implements BaseDTO<String> {

    private String id;
    private LegalDecisionTypeDTO documentType;
    private LocalDate documentDate;
    private String objectId;
    private Integer documentNumber;
    private List<LegalDecisionGroundTypeDTO> legalGroundTypes;
    private ObjectTypeDTO objectType;
    private String title;
    private AttachmentDTO attachment;
}
