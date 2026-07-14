package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.AutocompleteResultDTO;
import bg.duosoft.bpo.common.dto.filter.BaseFilterDTO;
import bg.duosoft.bpo.common.dto.filter.TextSearchType;
import bg.duosoft.bpo.publik.core.dto.nomenclature.BpoOnlineStatusDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.CountryDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RepresentativeTypeDTO;
import bg.duosoft.bpo.registers.dto.autocomplete.RepresentativeAutocompleteResultDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.02.2024
 * Time: 11:32
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonIpObjectFilterDetailsDTO extends BaseFilterDTO {

    private TextMatchFilterDetailsDTO objectName;
    private FromToFilterDetailsDTO<AutocompleteResultDTO> filingNumber;
    private FromToFilterDetailsDTO<LocalDate> filingDate;
    private FromToFilterDetailsDTO<AutocompleteResultDTO> registrationNumber;
    private FromToFilterDetailsDTO<LocalDate> expirationDate;
    private BpoOnlineStatusDTO status;
    private PublicationFilterDetailsDTO publications;
    private List<RepresentativeTypeDTO> representativeTypes;
    private String representativeName;
    private TextSearchType representativeNameSearchType;
    private List<RepresentativeAutocompleteResultDTO> representatives;
    private String applicantOwner;
    private TextSearchType applicantOwnerPersonSearchType;
    private CountryDTO applicantOwnerCountry;
    private List<String> objectRange;
    private PriorityFilterDetailsDTO priority;
}
