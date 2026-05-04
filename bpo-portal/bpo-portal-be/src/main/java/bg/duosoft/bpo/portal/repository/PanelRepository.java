package bg.duosoft.bpo.portal.repository;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.portal.entity.PanelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PanelRepository extends BaseRepository<String, PanelEntity> {

    @Query("select pe from PanelEntity pe left join fetch pe.childrenPanels where pe.parentPanelId is null order by pe.panelOrder")
    List<PanelEntity> getAllByParentPanelIdNull();

    @Query("select pe from PanelEntity pe left join fetch pe.childrenPanels where pe.id=:panelId order by pe.panelOrder")
    PanelEntity findPanelById(@Param("panelId") String panelId);

    @Query("select pe.id as key, new bg.duosoft.bpo.portal.dto.Breadcrumb(pe.labelBg, pe.labelEn) as value from PanelEntity pe where pe.parentPanelId is null or pe.parentPanelId='services'")
    List<Object[]> getPanelBreadcrumbData();
}
