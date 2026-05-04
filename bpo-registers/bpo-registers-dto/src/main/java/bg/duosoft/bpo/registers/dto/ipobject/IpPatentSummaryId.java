package bg.duosoft.bpo.registers.dto.ipobject;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:15
 */
@Data
public class IpPatentSummaryId implements Serializable {

    private String patentId;
    private String languageCode;
}
