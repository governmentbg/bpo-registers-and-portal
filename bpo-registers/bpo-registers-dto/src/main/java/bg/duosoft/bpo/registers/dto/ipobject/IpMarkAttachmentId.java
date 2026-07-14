package bg.duosoft.bpo.registers.dto.ipobject;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class IpMarkAttachmentId implements Serializable {
    private String markId;
    private Integer attachmentSequenceNumber;
}