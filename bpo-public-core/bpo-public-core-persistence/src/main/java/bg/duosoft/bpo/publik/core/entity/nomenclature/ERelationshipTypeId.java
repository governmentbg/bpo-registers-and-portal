package bg.duosoft.bpo.publik.core.entity.nomenclature;


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
public class ERelationshipTypeId implements Serializable {

    @Column(name = "relationship_typ")
    private String relationshipType;

    @Column(name = "application_typ")
    private String applicationType;
}
