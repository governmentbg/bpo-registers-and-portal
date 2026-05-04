package bg.duosoft.bpo.registers.service.ipobject;

import bg.duosoft.bpo.registers.dto.ipobject.IpMarkAttachmentDTO;

public interface IpMarkAttachmentService {
    IpMarkAttachmentDTO selectMarkAttachmentByObjectId(String objectId, Integer sequenceNumber);
}
