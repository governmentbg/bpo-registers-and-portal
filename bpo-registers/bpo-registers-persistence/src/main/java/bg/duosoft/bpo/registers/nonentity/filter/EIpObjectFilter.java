package bg.duosoft.bpo.registers.nonentity.filter;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EIpObjectFilter extends EExtraDataFilter {
    private String name;
    private ETextSearchType nameSearchType;
    private String filingNumberFrom;
    private String filingNumberTo;
    private LocalDate filingDateFrom;
    private LocalDate filingDateTo;
    private Integer registrationNumberFrom;
    private Integer registrationNumberTo;
    private LocalDate expirationDateFrom;
    private LocalDate expirationDateTo;
    private String statusCode;
    private Integer publicationYear;
    private String publicationNumber;
    private String publicationSection;
    private LocalDate publicationDateFrom;
    private LocalDate publicationDateTo;
    private String representativeTypeCode;
    private String representativeName;
    private ETextSearchType representativeNameSearchType;
    private List<String> representativeCodes;
    private ETextSearchType applicantOwnerPersonSearchType;
    private String applicantOwner;
    private String applicantOwnerCountry;
    private List<String> objectRange;
    private List<String> objectSubType;
    //DESIGNS
    private String locarnoClass;
    private String locarnoSubclass;
    private String designIdentifierFrom;
    private String designIdentifierTo;
    //MARKLIKE
    private String markKind;
    private List<String> viennaClassCodes;
    private ESearchOperatorType viennaClassCodeType;
    private List<Integer> niceClassCodes;
    private ESearchOperatorType niceClassCodeType;
    //PATENTLIKE
    private String abstractText;
    private ETextSearchType abstractSearchType;
    private LocalDate priorityDateFrom;
    private LocalDate priorityDateTo;
    private String priorityCountry;
    private String priorityNumber;
    private List<String> ipcClasses;
    private ESearchOperatorType ipcClassOperatorType;
    private String inventor;
    private ETextSearchType inventorPersonSearchType;
    //PLANT
    private String latinClassification;
    private String bgClassification;
}
