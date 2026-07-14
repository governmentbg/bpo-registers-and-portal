package bg.duosoft.bpo.registers.repository.legaldecision.custom;

import bg.duosoft.bpo.registers.entity.legaldecision.ELegalDecisionDocument;
import bg.duosoft.bpo.registers.nonentity.filter.ELegalDecisionDocumentDataFilter;

import java.util.List;

public interface LegalDecisionDocumentRepositoryCustom {

    List<ELegalDecisionDocument> filterLegalDecisions(ELegalDecisionDocumentDataFilter filter);

    Integer countDocuments(ELegalDecisionDocumentDataFilter filter);
}
