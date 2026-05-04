package bg.duosoft.bpo.registers.repository.ipobject;


import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpMark;
import org.springframework.data.jpa.repository.Query;


public interface IpMarkRepository extends BaseRepository<String, EIpMark> {

    @Query(value = "SELECT COUNT(*) FROM ip_objects.ip_mark e WHERE e.mark_id = ?1", nativeQuery = true)
    Integer countById(String id);


    @Query(value = "select ip_objects.delete_mark_object(?1)", nativeQuery = true)
    Integer deleteMark(String id);

}
