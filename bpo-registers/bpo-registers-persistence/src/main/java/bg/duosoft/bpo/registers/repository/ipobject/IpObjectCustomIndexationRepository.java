package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectCustomIndexation;
import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectCustomReplication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IpObjectCustomIndexationRepository extends BaseRepository<String, EIpObjectCustomIndexation> {

    @Query(value = "select object_id from ip_objects.ip_object_custom_indexation where replicator_type = ?1", nativeQuery = true)
    List<String> selectNotIndexedObjectIdsByReplicatorType(String replicatorType);


    @Modifying
    @Query(value = "delete from ip_objects.ip_object_custom_indexation where replicator_type = ?1 ", nativeQuery = true)
    void deleteCustomIndexationsByReplicatorType(String replicatorType);


}
