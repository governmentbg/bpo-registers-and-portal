package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VwObjectRelationshipDTO implements BaseDTO<VwObjectRelationshipIdDTO> {
    private VwObjectRelationshipIdDTO id;
    private String relationshipName;
    private String relationshipNameEn;
    private String registrationNumber;
    private LocalDate registrationDate;
    private String registrationCountry;
    private LocalDate cancellationDate;
    private LocalDate priorityDate;
    private LocalDate serveMessageDate;
    private String relatedObjectId;
}
