package bg.duosoft.bpo.registers.entity.ipobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 02.02.2024
 * Time: 14:30
 */
@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EIpPatentCitationId implements Serializable {
    @Column(name = "patent_id")
    private String patentId;
    @Column(name = "ref_nbr")
    private Integer refNumber;
}
