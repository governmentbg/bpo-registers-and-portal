package bg.duosoft.bpo.registers.dto.ipobject;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class VwPersonObjectRelationshipIdDTO implements Serializable {
    private String objectId;
    private String roleCode;
    private Integer personId;
}
