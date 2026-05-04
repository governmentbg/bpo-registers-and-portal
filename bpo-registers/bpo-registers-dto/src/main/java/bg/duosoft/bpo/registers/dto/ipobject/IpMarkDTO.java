package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.MarkKindDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:03
 */
@Data
public class IpMarkDTO implements IpObjectBaseDTO, BaseDTO<String> {

    private String id;
    private MarkKindDTO markKind;
    private String markDescription;
    private String markTransliteration;
    private String markTranslation;
    private String disclaimer;
    private LocalDate statusChangedDate;
    private LocalDate oppositionDate;
    private LocalDate oppositionEndDate;
    private IpObjectDTO ipObject;
    private List<IpMarkNiceClassDTO> niceClasses;
    private List<IpMarkAttachmentDTO> markAttachments;
    private String st13;
    private Boolean acquiredDistinctiveness;
}
