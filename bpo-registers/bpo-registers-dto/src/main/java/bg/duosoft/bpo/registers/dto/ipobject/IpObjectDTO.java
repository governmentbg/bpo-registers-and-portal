package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ObjectSubtypeDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ObjectTypeDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.StatusMapDTO;
import bg.duosoft.bpo.registers.dto.recordal.RecordalDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:05
 */
@Data
public class IpObjectDTO implements BaseDTO<String> {

    private String id;
    private String alternateKey;
    private ObjectTypeDTO objectType;
    private String title;
    private String titleEn;
    private String notes;
    private LocalDate filingDate;
    private LocalDate statusDate;
    private LocalDate entitlementDate;
    private LocalDate expirationDate;
    private LocalDate registrationDate;
    private LocalDate dateUpdated;
    private String registrationNumber;
    private ObjectSubtypeDTO objectSubType;
    private StatusMapDTO status;
    private List<IpObjectRelationshipDTO> directRelationships;
    private List<IpObjectRelationshipDTO> inverseRelationships;
    private List<IpObjectPriorityDTO> priorities;
    private List<IpObjectPublicationDTO> publications;
    private List<IpPersonToIpObjectDTO> persons;
    private List<RecordalDTO> recordals;
    private List<IpObjectAttachmentDTO> attachments;
}
