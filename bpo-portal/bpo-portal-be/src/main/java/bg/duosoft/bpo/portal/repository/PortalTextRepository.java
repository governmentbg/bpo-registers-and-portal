package bg.duosoft.bpo.portal.repository;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.portal.entity.PortalTextEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PortalTextRepository extends BaseRepository<String, PortalTextEntity> {

    @Query("FROM PortalTextEntity AS p WHERE p.dynamicContent=:active AND :currentDateAndTime BETWEEN p.activeFrom AND p.activeTo")
    List<PortalTextEntity> getActivePortalText(@Param("active") Integer dynamicContent, @Param("currentDateAndTime") LocalDateTime localDateTime);
}
