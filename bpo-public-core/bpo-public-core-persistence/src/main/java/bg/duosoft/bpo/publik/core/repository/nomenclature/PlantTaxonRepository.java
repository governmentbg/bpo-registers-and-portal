package bg.duosoft.bpo.publik.core.repository.nomenclature;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPlantTaxon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 05.05.2022
 * Time: 12:02
 */
public interface PlantTaxonRepository extends BaseRepository<Integer, EPlantTaxon> {
    @Query("SELECT distinct x.commonClassifyBg from EPlantTaxon x")
    List<String> getAllBgClassifications();

    @Query("SELECT distinct x.latinClassify from EPlantTaxon x")
    List<String> getAllLatinClassifications();

    @Query("""
            SELECT p FROM EPlantTaxon p WHERE LOWER(p.commonClassifyBg)
                        LIKE CONCAT('%', LOWER(:word), '%') OR LOWER(p.latinClassify) LIKE CONCAT('%',LOWER(:word), '%')
                                    ORDER BY  p.latinClassify LIMIT :limit
            """)
    List<EPlantTaxon> getAutocompletePlantTaxon(@Param("word") String word, @Param("limit") Integer limit);
}
