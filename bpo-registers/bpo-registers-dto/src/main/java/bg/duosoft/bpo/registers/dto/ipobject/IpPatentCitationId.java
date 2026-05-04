package bg.duosoft.bpo.registers.dto.ipobject;

import lombok.*;

import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 02.02.2024
 * Time: 14:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IpPatentCitationId implements Serializable {
    private String patentId;
    private Integer refNumber;
}
