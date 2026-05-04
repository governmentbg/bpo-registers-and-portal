package bg.duosoft.bpo.registers.dto.ipobject;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class IpSingleDesignLocarnoId implements Serializable {
    private String singleDesignId;

    private String locarnoClassCode;
}
