package bg.duosoft.bpo.publik.core.dto.nomenclature;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class RelationshipTypeId implements Serializable {

    private String relationshipType;

    private String applicationType;
}
