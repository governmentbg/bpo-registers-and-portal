package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.UserAreaReplicationEntity;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

/**
 * User: ggeorgiev
 * Date: 07.05.2025
 * Time: 11:48
 */
public interface UserAreaReplicationRepository extends BaseRepository<Integer, UserAreaReplicationEntity> {
    @Query(value = "select date_start from ip_objects.userarea_replications order by date_start desc limit 1", nativeQuery = true)
    LocalDateTime selectMaxReplicationDate();
}
