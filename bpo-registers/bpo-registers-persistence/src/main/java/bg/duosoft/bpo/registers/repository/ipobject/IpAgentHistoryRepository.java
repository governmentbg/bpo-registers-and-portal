package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpAgentHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IpAgentHistoryRepository extends BaseRepository<Integer, EIpAgentHistory> {

    @Query(value = "SELECT h FROM EIpAgentHistory h WHERE h.personId = :personId")
    List<EIpAgentHistory> selectAllByPersonId(@Param("personId") Integer personId);
    boolean existsByBoHistoryId(@Param("boHistoryId") String boHistoryId);
}
