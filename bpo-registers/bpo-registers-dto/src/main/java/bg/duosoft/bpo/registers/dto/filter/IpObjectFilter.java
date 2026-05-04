package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.BaseFilterDTO;
import bg.duosoft.bpo.common.dto.filter.SearchOperatorType;
import bg.duosoft.bpo.common.dto.filter.TextSearchType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpObjectFilter extends BaseFilterDTO {
    private String name;
    private TextSearchType nameSearchType;
    private String filingNumberFrom;
    private String filingNumberTo;
    private LocalDate filingDateFrom;
    private LocalDate filingDateTo;
    private Integer registrationNumberFrom;
    private Integer registrationNumberTo;
    private LocalDate expirationDateFrom;
    private LocalDate expirationDateTo;
    private List<String> statusCodes;
    private Integer publicationYear;
    private String publicationNumber;
    private Integer publicationSection;
    private LocalDate publicationDateFrom;
    private LocalDate publicationDateTo;
    private List<String> representativeTypeCodes;
    private String representativeName;
    private TextSearchType representativeNameSearchType;
    private List<String> representativeCodes;
    private TextSearchType applicantOwnerPersonSearchType;
    private String applicantOwner;
    private String applicantOwnerCountry;
    private List<String> objectRange;
    private List<String> objectSubType;
    //DESIGNS
    private List<String> locarnoClassCodes;
    private SearchOperatorType locarnoClassCodeType;
    private String designIdentifierFrom;
    private String designIdentifierTo;
    private String singleDesignName;
    private TextSearchType singleDesignNameSearchType;
    private String singleDesignVerbalElement;
    private TextSearchType singleDesignVerbalElementSearchType;
    //MARKLIKE
    private String markKind;
    private List<String> viennaClassCodes;
    private SearchOperatorType viennaClassCodeType;
    private List<Integer> niceClassCodes;
    private SearchOperatorType niceClassCodeType;
    private String niceClassSpecificationText;
    private TextSearchType niceClassSpecificationSearchType;
    //PATENTLIKE
    private String abstractText;
    private TextSearchType abstractSearchType;
    private LocalDate priorityDateFrom;
    private LocalDate priorityDateTo;
    private String priorityCountry;
    private String priorityNumber;
    private List<String> ipcClasses;
    private String ipcCode;
    private SearchOperatorType ipcClassOperatorType;
    private List<String> cpcClasses;
    private String cpcCode;
    private SearchOperatorType cpcClassOperatorType;
    private String inventor;
    private TextSearchType inventorPersonSearchType;
    private String inventorCountry;
    //PLANT
    private String latinClassification;
    private String bgClassification;
}
