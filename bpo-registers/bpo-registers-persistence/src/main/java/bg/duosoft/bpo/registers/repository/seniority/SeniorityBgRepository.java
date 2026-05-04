package bg.duosoft.bpo.registers.repository.seniority;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.seniority.ESeniorityBg;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 09.02.2026
 * Time: 17:46
 */
public interface SeniorityBgRepository extends BaseRepository<Integer, ESeniorityBg> {
    public List<ESeniorityBg> getESeniorityBgByObjectId(String objectId);
    @Query("select count(s) from ESeniorityBg s where s.objectId = :objectId")
    public Long getSeniorityCount(@Param("objectId") String objectId);
}
