package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * User: ggeorgiev
 * Date: 02.02.2024
 * Time: 14:30
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_patent_citation", schema = "ip_objects")
@EqualsAndHashCode
public class EIpPatentCitation  implements BaseEntity<EIpPatentCitationId> {
    @EmbeddedId
    private EIpPatentCitationId id;
    @Column(name = "ref_description")
    private String refDescription;
    @Column(name = "ref_claims")
    private String refClaims;

}
