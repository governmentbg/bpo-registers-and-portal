package bg.duosoft.bpo.publik.core.repository.nomenclature;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ENiceClass;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NiceClassRepository extends BaseRepository<Integer, ENiceClass> {
    @Query("""
            SELECT niceClass FROM ENiceClass niceClass WHERE niceClass.id <= :endId ORDER BY niceClass.id""")
    List<ENiceClass> selectNiceClassesRange(@Param("endId") Integer endId);
}
