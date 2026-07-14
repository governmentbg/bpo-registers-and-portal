package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPlant;
import org.springframework.data.jpa.repository.Query;

public interface IpPlantRepository extends BaseRepository<String, EIpPlant> {

    @Query(value = "SELECT COUNT(*) FROM ip_objects.ip_plant e WHERE e.plant_id = ?1", nativeQuery = true)
    Integer countById(String id);

    @Query(value = "select ip_objects.delete_plant_object(?1)", nativeQuery = true)
    Integer deletePlant(String id);

}
