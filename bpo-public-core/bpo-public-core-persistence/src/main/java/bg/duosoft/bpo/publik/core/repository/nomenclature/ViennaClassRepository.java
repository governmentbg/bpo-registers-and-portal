package bg.duosoft.bpo.publik.core.repository.nomenclature;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EViennaClass;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 13.02.2024
 * Time: 13:28
 */
public interface ViennaClassRepository extends BaseRepository<String, EViennaClass> {

    @Query("SELECT v FROM EViennaClass v ORDER BY v.id ASC")
    List<EViennaClass> findAll();

    @Query("SELECT v FROM EViennaClass v WHERE v.id LIKE :idSearchText OR lower(v.name) LIKE lower(:nameSearchText) ORDER BY v.id ASC")
    List<EViennaClass> findAllWithNameOrIdLike(@Param("idSearchText") String idSearchText, @Param("nameSearchText") String nameSearchText, Pageable pageable);
}
