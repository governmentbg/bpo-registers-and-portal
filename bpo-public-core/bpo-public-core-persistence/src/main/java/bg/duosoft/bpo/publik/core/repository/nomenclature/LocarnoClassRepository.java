package bg.duosoft.bpo.publik.core.repository.nomenclature;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ELocarnoClass;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 14.11.2024
 * Time: 16:34
 */
public interface LocarnoClassRepository extends BaseRepository<String, ELocarnoClass> {
    @Query("SELECT v FROM ELocarnoClass v ORDER BY v.id ASC")
    List<ELocarnoClass> findAll();

    @Query("SELECT v FROM ELocarnoClass v WHERE v.id LIKE :idSearchText OR lower(v.description) LIKE lower(:nameSearchText) ORDER BY v.id ASC")
    List<ELocarnoClass> findAllWithDescriptionOrIdLike(@Param("idSearchText") String idSearchText, @Param("nameSearchText") String nameSearchText, Pageable pageable);
}
