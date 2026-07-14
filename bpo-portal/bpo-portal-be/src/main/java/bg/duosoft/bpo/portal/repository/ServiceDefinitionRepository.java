package bg.duosoft.bpo.portal.repository;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.portal.entity.ServiceDefinitionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceDefinitionRepository extends BaseRepository<String, ServiceDefinitionEntity> {

    @Query("SELECT sd FROM ServiceDefinitionEntity sd WHERE sd.panelId=:panelId order by sd.serviceOrder")
    List<ServiceDefinitionEntity> findAllServicesByPanelId(@Param("panelId") String panelId);

    @Query("select sd.id as key, new bg.duosoft.bpo.portal.dto.Breadcrumb(sd.labelBg, sd.labelEn) as value from ServiceDefinitionEntity sd")
    List<Object[]> getServiceDefinitionBreadcrumbData();
}
