package bg.duosoft.bpo.registers.repository.ipobject;


import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpReplicationTime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IpReplicationTimeRepository extends BaseRepository<Integer, EIpReplicationTime> {

    @Query(value = "select date_start from ip_objects.ip_replication_times where id  = (select max(id) from ip_objects.ip_replication_times where replicator_type = ?1 and date_end is not null)", nativeQuery = true)
    LocalDateTime selectMaxReplicationDateByReplicatorType(String replicatorType);

    @Modifying
    @Query(value = "update ip_objects.ip_replication_times set modified_rows = modified_rows + 1 where id = ?1 ", nativeQuery = true)
    void updateOnInsertedObject(Integer replicationTimeId);

    @Modifying
    @Query(value = "update EIpReplicationTime set xmlGenerated = :xmlGenerated where id in :ids")
    void updateXmlGenerated(@Param("ids") List<Integer> replicationTimeId, @Param("xmlGenerated") Integer xmlGenerated);

    @Modifying
    @Query(value = "update EIpReplicationTime set indexed = :indexed where id in :ids")
    void updateIndexed(@Param("ids") List<Integer> replicationTimeId, @Param("indexed") Integer indexed);

    @Query(value = "select rt from EIpReplicationTime rt where rt.xmlGenerated = 0 and rt.replicatorType = :replicatorType and rt.dateEnd is not null")
    List<EIpReplicationTime> getNotXmlGeneratedReplicationTimes(@Param("replicatorType") String replicatorType);

    @Query(value = "select rt from EIpReplicationTime rt where rt.replicatorType = :replicatorType and rt.dateEnd is not null")
    List<EIpReplicationTime> getAllReplicationTimes(@Param("replicatorType") String replicatorType);

    @Query(value = "select rt from EIpReplicationTime rt where rt.indexed = 0 and rt.replicatorType = :replicatorType and rt.dateEnd is not null")
    List<EIpReplicationTime> getNotIndexedReplicationTimes(@Param("replicatorType") String replicatorType);

    @Query(value = "select rt.modifiedRows from EIpReplicationTime rt where rt.dateStart between :start and :end and rt.xmlGenerated = 1 and rt.replicatorType = :replicatorType and rt.dateEnd is not null")
    List<Integer> getModifiedRows(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("replicatorType") String replicatorType);

    @Modifying
    @Query(value = "update EIpReplicationTime set dateEnd = :dateEnd where id in :id")
    void updateDateEnd(@Param("id") Integer id, @Param("dateEnd") LocalDateTime dateEnd);

    @Query(value = "select rt from EIpReplicationTime rt where rt.exported = 0 and rt.replicatorType = 'MARK' and rt.dateEnd is not null")
    List<EIpReplicationTime> getNonExportedMarkReplicationTimes();

    @Modifying
    @Query(value = "update EIpReplicationTime set exported = 1")
    void setExportedTrueForAll();


}
