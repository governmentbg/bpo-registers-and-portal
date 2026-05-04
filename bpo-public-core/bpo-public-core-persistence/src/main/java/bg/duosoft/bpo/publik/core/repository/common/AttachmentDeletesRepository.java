package bg.duosoft.bpo.publik.core.repository.common;

import bg.duosoft.bpo.common.repository.BaseRepository;

import bg.duosoft.bpo.publik.core.entity.common.EAttachmentDeletes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface AttachmentDeletesRepository extends BaseRepository<Integer, EAttachmentDeletes> {
    public List<EAttachmentDeletes> getByStatus(int status);
    @Modifying
    @Query("UPDATE EAttachmentDeletes set status = :status where id = :id")
    public void updateStatus(@Param("status")Integer status, @Param("id")Integer id);
}
