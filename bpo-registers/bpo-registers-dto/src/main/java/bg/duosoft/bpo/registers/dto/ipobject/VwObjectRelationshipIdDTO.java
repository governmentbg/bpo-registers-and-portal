package bg.duosoft.bpo.registers.dto.ipobject;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class VwObjectRelationshipIdDTO implements Serializable {
    private String objectId;
    private String relationshipType;
    private String applicationType;
    private String objectReference;
    private String description;
    private String descriptionEn;
}
