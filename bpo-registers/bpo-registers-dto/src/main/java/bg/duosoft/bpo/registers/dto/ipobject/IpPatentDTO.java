package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:13
 */
@Data
public class IpPatentDTO implements IpObjectBaseDTO, BaseDTO<String> {

    private String id;
    private Integer drawingsCount;
    private Integer claimsCount;
    private Integer descriptionPagesCount;
    private Integer drawingPublication;
    private Integer inventionsGroupCount;
    private LocalDate renewalFeesLastPaid;
    private LocalDate renewalFeesPaidTo;
    private Integer lastPaidYear;
    private LocalDate notInForceDate;
    private LocalDate effectiveDate;
    private IpObjectDTO ipObject;
    private List<IpPatentSummaryDTO> patentSummaries;
    private IpPatentPctDTO pct;
    private String mainAbstract;
    private String enAbstract;
    private List<IpPatentIpcClassDTO> ipcClasses;
    private List<IpPatentCpcClassDTO> cpcClasses;
    private List<IpPatentCitationDTO> citations;
}
