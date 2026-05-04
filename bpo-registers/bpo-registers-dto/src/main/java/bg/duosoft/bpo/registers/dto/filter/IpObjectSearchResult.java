package bg.duosoft.bpo.registers.dto.filter;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@EqualsAndHashCode
@Builder
public class IpObjectSearchResult implements Serializable {
    private String objectId;
    private String objectType;
    private String objectSubtype;
    private Integer filingNumber;
    private LocalDate filingDate;
    private LocalDate registrationDate;
    private String registrationNbr;
    private String mainOwner;
    private String title;
    private Boolean hasImg;
    private String status;
    private String statusName;
    private String markKind;
    private List<Integer> niceClasses;
}
