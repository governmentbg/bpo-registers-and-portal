package bg.duosoft.bpo.publik.core.repository.nomenclature;


import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPatentIpcClass;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPatentIpcClassId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatentIpcClassRepository extends BaseRepository<EPatentIpcClassId, EPatentIpcClass> {

    @Query("SELECT v FROM EPatentIpcClass v WHERE lower(concat(v.id.sectionCode, v.id.classCode, v.id.subclassCode, v.id.groupCode, v.id.subgroupCode, v.id.editionCode)) LIKE lower(:nameSearchText) ORDER BY v.id.sectionCode, v.id.classCode, v.id.subclassCode, v.id.groupCode, v.id.subgroupCode, v.id.editionCode ASC")
    List<EPatentIpcClass> findAllWithDescriptionOrIdLike(@Param("nameSearchText") String nameSearchText, Pageable pageable);

}
