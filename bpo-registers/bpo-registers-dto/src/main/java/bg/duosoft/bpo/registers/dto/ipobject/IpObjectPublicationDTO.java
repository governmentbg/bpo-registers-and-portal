package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PublicationSectionDTO;
import lombok.Data;

import java.time.LocalDate;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:29
 */
@Data
public class IpObjectPublicationDTO implements BaseDTO<Integer> {
    private Integer id;
    private String objectId;
    private String publicationNumber;
    private Integer publicationYear;
    private PublicationSectionDTO publicationSection;
    private Integer seqNbr;
    private LocalDate publicationDate;
    private Integer journalNbr;
    private Integer elementNbr;
    private Integer journalStructNodeNbr;
    private Boolean isPublic;
}
