package bg.duosoft.bpo.registers.repository.legaldecision;


import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.legaldecision.ELegalDecisionReplicationTime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface LegalDecisionReplicationTimeRepository extends BaseRepository<Integer, ELegalDecisionReplicationTime> {

    @Query(value = "select date_start from legal_decisions.replication_times where date_end is not null order by id desc limit 1", nativeQuery = true)
    LocalDateTime selectMaxReplicationDate();

    @Modifying
    @Query(value = "update legal_decisions.replication_times set modified_rows = modified_rows + 1 where id = ?1 ", nativeQuery = true)
    void updateOnInsertedObject(Integer replicationTimeId);

    @Modifying
    @Query(value = "update ELegalDecisionReplicationTime set dateEnd = :dateEnd where id in :id")
    void updateDateEnd(@Param("id") Integer id, @Param("dateEnd") LocalDateTime dateEnd);
}
