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
public class EIpObjectRelationshipId implements Serializable {

    @Column(name = "object_id1")
    private String objectId1;

    @Column(name = "object_id2")
    private String objectId2;

    @Column(name = "relationship_typ")
    private String relationshipType;

    @Column(name = "application_typ")
    private String applicationType;

}
