package bg.duosoft.bpo.publik.core.repository.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.EObjectSubtype;
import bg.duosoft.bpo.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObjectSubTypeRepository extends BaseRepository<String, EObjectSubtype> {
    @Query("SELECT st from EObjectSubtype st where st.objectType.id in ( :objectRange )")
    List<EObjectSubtype> selectByObjectTypeIn(List<String> objectRange);
}
