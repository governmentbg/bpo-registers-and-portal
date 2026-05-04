package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RelationshipTypeDTO;
import lombok.Data;

import java.time.LocalDate;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:22
 */
@Data
public class IpObjectRelationshipDTO implements BaseDTO<IpObjectRelationshipId> {

    private IpObjectRelationshipId id;

    private String filingNumber;
    private LocalDate filingDate;
    private String registrationNumber;
    private LocalDate registrationDate;
    private String registrationCountry;
    private LocalDate cancellationDate;
    private LocalDate priorityDate;
    private LocalDate serveMessageDate;
    private RelationshipTypeDTO relationshipType;
}
