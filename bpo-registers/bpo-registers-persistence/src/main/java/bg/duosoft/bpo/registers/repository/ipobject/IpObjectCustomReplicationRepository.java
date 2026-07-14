package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectCustomReplication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IpObjectCustomReplicationRepository extends BaseRepository<String, EIpObjectCustomReplication> {

    @Query(value = "select object_id from ip_objects.ip_object_custom_replication where replicator_type = ?1", nativeQuery = true)
    List<String> selectNotReplicatedObjectIdsByReplicatorType(String replicatorType);


    @Modifying
    @Query(value = "delete from ip_objects.ip_object_custom_replication where replicator_type = ?1 ", nativeQuery = true)
    void deleteCustomReplicationsByReplicatorType(String replicatorType);


}
