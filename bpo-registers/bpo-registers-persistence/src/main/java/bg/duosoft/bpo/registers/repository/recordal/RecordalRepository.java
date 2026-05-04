package bg.duosoft.bpo.registers.repository.recordal;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.recordal.ERecordal;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordalRepository extends BaseRepository<String, ERecordal> {

    @Query(value = "SELECT r.* from ip_object_recordals.recordals r join nomenclatures.recordal_type rte on rte.id = r.recordal_type_id where group_type not in (?1) and ip_object_id = ?2 order by filing_date asc", nativeQuery = true)
    List<ERecordal> getRecordalsWithExcludedGroupsByObjectId(List<String> groupIds, String objectId);

    @Query(value = "SELECT r.* from ip_object_recordals.recordals r join nomenclatures.recordal_type rte on rte.id = r.recordal_type_id where group_type in (?1) and ip_object_id = ?2 order by filing_date asc", nativeQuery = true)
    List<ERecordal> getRecordalsWithIncludedGroupsByObjectId(List<String> groupIds, String objectId);
}
