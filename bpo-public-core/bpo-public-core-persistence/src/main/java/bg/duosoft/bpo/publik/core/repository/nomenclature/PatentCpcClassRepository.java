package bg.duosoft.bpo.publik.core.repository.nomenclature;


import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPatentCpcClass;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPatentCpcClassId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatentCpcClassRepository extends BaseRepository<EPatentCpcClassId, EPatentCpcClass> {

    @Query("SELECT v FROM EPatentCpcClass v WHERE lower(concat(v.id.sectionCode, v.id.classCode, v.id.subclassCode, v.id.groupCode, v.id.subgroupCode, v.id.editionCode)) LIKE lower(:nameSearchText) ORDER BY v.id.sectionCode, v.id.classCode, v.id.subclassCode, v.id.groupCode, v.id.subgroupCode, v.id.editionCode ASC")
    List<EPatentCpcClass> findAllWithDescriptionOrIdLike(@Param("nameSearchText") String nameSearchText, Pageable pageable);

}
