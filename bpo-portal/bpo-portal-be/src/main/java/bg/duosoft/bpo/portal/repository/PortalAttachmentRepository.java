package bg.duosoft.bpo.portal.repository;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.portal.entity.PortalAttachmentEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PortalAttachmentRepository extends BaseRepository<String, PortalAttachmentEntity> {

    @Query("FROM PortalAttachmentEntity AS p WHERE p.visibleFlag <> 0 order by p.position ")
    List<PortalAttachmentEntity> getAllPortalAttachmentsOrderByPosition();

    @Query("FROM PortalAttachmentEntity AS p WHERE p.id = :id")
    PortalAttachmentEntity getPortalAttachmentById(@Param("id") String id);
}
