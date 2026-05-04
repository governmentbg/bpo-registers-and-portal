package bg.duosoft.bpo.registers.entity.ipobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
public class VwPersonObjectRelationshipId implements Serializable {

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "person_id")
    private Integer personId;

}
