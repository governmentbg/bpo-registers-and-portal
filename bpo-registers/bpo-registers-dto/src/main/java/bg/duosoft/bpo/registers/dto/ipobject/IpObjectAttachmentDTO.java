package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.common.AttachmentDTO;
import lombok.Data;

@Data
public class IpObjectAttachmentDTO implements BaseDTO<IpObjectAttachmentId> {
    private IpObjectAttachmentId id;
    private AttachmentDTO attachment;
}
