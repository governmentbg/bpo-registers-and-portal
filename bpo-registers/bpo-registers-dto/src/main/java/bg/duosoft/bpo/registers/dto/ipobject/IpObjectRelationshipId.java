package bg.duosoft.bpo.registers.dto.ipobject;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class IpObjectRelationshipId implements Serializable {

    private String objectId1;

    private String objectId2;

    private String relationshipType;

    private String applicationType;

}
