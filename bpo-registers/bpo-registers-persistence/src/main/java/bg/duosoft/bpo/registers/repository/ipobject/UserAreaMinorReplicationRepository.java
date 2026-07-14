package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.UserAreaMinorReplicationEntity;
import bg.duosoft.bpo.registers.entity.ipobject.UserAreaReplicationEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

/**
 * User: ggeorgiev
 * Date: 07.05.2025
 * Time: 11:48
 */
public interface UserAreaMinorReplicationRepository extends BaseRepository<Integer, UserAreaMinorReplicationEntity> {
    @Query(value = "select date_start from ip_objects.userarea_minor_replications order by date_start desc limit 1", nativeQuery = true)
    LocalDateTime selectMaxReplicationDate();

    @Modifying
    @Query(value = """
                    DELETE FROM ip_objects.userarea_minor_replications
                    WHERE id NOT IN (
                        SELECT id FROM ip_objects.userarea_minor_replications
                        ORDER BY id DESC
                        LIMIT 10
                    )""", nativeQuery = true)
    void deleteAllButLastTenRecords();
}
