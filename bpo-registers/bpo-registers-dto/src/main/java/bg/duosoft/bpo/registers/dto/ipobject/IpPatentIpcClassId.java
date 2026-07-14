package bg.duosoft.bpo.registers.dto.ipobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class IpPatentIpcClassId implements Serializable {

    private String patentId;

    private String editionCode;

    private String sectionCode;

    private String classCode;

    private String subclassCode;

    private String groupCode;

    private String subgroupCode;

}