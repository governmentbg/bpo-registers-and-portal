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
public class VwObjectRelationshipId implements Serializable {

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "relationship_typ")
    private String relationshipType;

    @Column(name = "application_typ")
    private String applicationType;

    @Column(name = "object_reference")
    private String objectReference;

    @Column(name = "description")
    private String description;

    @Column(name = "description_en")
    private String descriptionEn;

}
