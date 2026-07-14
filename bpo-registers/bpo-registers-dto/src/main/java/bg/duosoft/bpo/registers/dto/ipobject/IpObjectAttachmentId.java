package bg.duosoft.bpo.registers.dto.ipobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class IpObjectAttachmentId implements Serializable {

    private String objectId;

    private Integer sequenceNumber;

}