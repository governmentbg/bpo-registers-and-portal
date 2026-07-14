package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpDesign;
import org.springframework.data.jpa.repository.Query;

public interface IpDesignRepository extends BaseRepository<String, EIpDesign> {
    @Query(value = "select ip_objects.delete_design_object(?1)", nativeQuery = true)
    Integer deleteDesign(String id);
}
