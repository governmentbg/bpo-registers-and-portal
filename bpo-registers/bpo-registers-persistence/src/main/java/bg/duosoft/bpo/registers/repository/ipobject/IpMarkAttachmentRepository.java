package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpMarkAttachment;
import bg.duosoft.bpo.registers.entity.ipobject.EIpMarkAttachmentId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IpMarkAttachmentRepository extends BaseRepository<EIpMarkAttachmentId, EIpMarkAttachment> {

    @Query(value = "SELECT a FROM EIpMarkAttachment a WHERE a.id.markId = :markId and a.id.attachmentSequenceNumber = :sequenceNumber")
    EIpMarkAttachment selectByMarkIdAndSequenceNumber(@Param("markId") String markId, @Param("sequenceNumber")Integer sequenceNumber);
}
