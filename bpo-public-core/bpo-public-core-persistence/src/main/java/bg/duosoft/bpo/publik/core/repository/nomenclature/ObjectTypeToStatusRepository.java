package bg.duosoft.bpo.publik.core.repository.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.EObjectTypeToStatus;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EObjectTypeToStatusId;
import bg.duosoft.bpo.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObjectTypeToStatusRepository extends BaseRepository<EObjectTypeToStatusId, EObjectTypeToStatus> {

    @Query(value = "SELECT * FROM nomenclatures.object_type_to_status s where s.object_type in (:objectTypes)", nativeQuery = true)
    List<EObjectTypeToStatus> selectByObjectTypeIn(List<String> objectTypes);
}
