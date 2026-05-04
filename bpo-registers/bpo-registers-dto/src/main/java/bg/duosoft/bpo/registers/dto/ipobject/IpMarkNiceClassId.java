package bg.duosoft.bpo.registers.dto.ipobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class IpMarkNiceClassId implements Serializable {

    private String markId;

    private Integer classId;

}