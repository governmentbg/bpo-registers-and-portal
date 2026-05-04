package bg.duosoft.bpo.registers.dto.ipobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class IpPersonToIpObjectId implements Serializable {

    private String objectId;

    private Integer personId;

    private String personRole;

}