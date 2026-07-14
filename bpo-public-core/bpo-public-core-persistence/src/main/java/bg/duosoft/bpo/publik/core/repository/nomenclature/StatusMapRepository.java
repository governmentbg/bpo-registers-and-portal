package bg.duosoft.bpo.publik.core.repository.nomenclature;


import bg.duosoft.bpo.publik.core.entity.nomenclature.EStatusMap;
import bg.duosoft.bpo.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatusMapRepository extends BaseRepository<String, EStatusMap> {

    @Query("SELECT s.id FROM EStatusMap s WHERE s.bpoOnlineStatus = :bpoOnlineName OR s.bpoOnlineStatusEn = :bpoOnlineName ")
    List<String> getStatusCodesByBpoOnlineName(@Param("bpoOnlineName") String bpoOnlineName);

    @Query("SELECT s.bpoOnlineStatus FROM EStatusMap s WHERE s.id = :backofficeStatusCode")
    String getBpoOnlineStatusNameByBackofficeCode(@Param("backofficeStatusCode") String backofficeStatusCode);

    @Query("""
    SELECT DISTINCT  botm.objectType, sm.id, sm.backofficeStatusName
    FROM EStatusMap sm
    JOIN EBackofficeObjectTypeMap botm  ON sm.processType = botm.processType
    WHERE botm.objectType IN :objectTypes AND sm.forDeletion = 0
    ORDER BY sm.backofficeStatusName ASC""")
    List<Object[]> getBOStatusesByObjectTypes(@Param("objectTypes") List<String> objectTypes);


    @Query(value = """
    SELECT bsm.code, bsm.backoffice_status_name
    FROM nomenclatures.backoffice_status_map bsm
    JOIN nomenclatures.backoffice_userdoc_processes bup
    ON bsm.process_type = bup.process_type
    WHERE bsm.for_deletion = 0
    ORDER BY bsm.backoffice_status_name ASC 
    """, nativeQuery = true)
    List<Object[]> getBOUserdocStatuses();
}
