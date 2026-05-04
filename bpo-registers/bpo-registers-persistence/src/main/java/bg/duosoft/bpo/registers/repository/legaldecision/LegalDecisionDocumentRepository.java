package bg.duosoft.bpo.registers.repository.legaldecision;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.legaldecision.ELegalDecisionDocument;
import bg.duosoft.bpo.registers.repository.legaldecision.custom.LegalDecisionDocumentRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegalDecisionDocumentRepository extends BaseRepository<String, ELegalDecisionDocument>, LegalDecisionDocumentRepositoryCustom {
    @Query("select ld from ELegalDecisionDocument ld where ld.objectId = :objectId ")
    List<ELegalDecisionDocument> getAllByObjectId(@Param("objectId") String objectId);
}
