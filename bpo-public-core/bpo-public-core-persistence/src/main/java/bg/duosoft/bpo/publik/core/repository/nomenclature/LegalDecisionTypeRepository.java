package bg.duosoft.bpo.publik.core.repository.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.ELegalDecisionType;
import bg.duosoft.bpo.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LegalDecisionTypeRepository extends BaseRepository<Integer, ELegalDecisionType> {
    @Query("select e from ELegalDecisionType e where lower(e.name) = lower( :name ) ")
    public ELegalDecisionType findByName(@Param("name") String name);
    @Modifying
    @Query(value = "INSERT INTO nomenclatures.legal_decision_type (id, name, name_en) VALUES ( ?1, ?2, ?3 )", nativeQuery = true)
    public void insert(Integer id, String name, String nameEn);
}
