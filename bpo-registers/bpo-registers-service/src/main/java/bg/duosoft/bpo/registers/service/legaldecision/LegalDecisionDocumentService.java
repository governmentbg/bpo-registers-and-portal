package bg.duosoft.bpo.registers.service.legaldecision;

import bg.duosoft.bpo.registers.dto.filter.LegalDecisionFilter;
import bg.duosoft.bpo.registers.dto.legaldecision.LegalDecisionDocumentDTO;
import org.springframework.data.domain.Page;
import java.util.List;

public interface LegalDecisionDocumentService {
    Page<LegalDecisionDocumentDTO> filterDocuments(LegalDecisionFilter filter);

    LegalDecisionDocumentDTO getById(String id);

    List<LegalDecisionDocumentDTO> getAllByObjectId(String objectId);
}
