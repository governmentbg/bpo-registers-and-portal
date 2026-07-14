package bg.duosoft.bpo.portal.repository;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.portal.entity.AdminPanelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminPanelRepository extends BaseRepository<Integer, AdminPanelEntity> {
    @Query("SELECT ap FROM AdminPanelEntity ap WHERE ap.parentId IS NULL ORDER BY ap.position ASC")
    List<AdminPanelEntity> getAdminPanelData();
}
