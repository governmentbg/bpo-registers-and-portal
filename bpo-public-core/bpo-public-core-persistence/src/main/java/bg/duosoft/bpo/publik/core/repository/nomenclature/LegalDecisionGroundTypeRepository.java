package bg.duosoft.bpo.publik.core.repository.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.ELegalDecisionGroundType;
import bg.duosoft.bpo.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LegalDecisionGroundTypeRepository extends BaseRepository<Integer, ELegalDecisionGroundType> {
    @Modifying
    @Query(value = "INSERT INTO nomenclatures.legal_decision_ground_type (id, name, name_en) VALUES ( ?1, ?2, ?3 )", nativeQuery = true)
    public void insert(Integer id, String name, String nameEn);
}
