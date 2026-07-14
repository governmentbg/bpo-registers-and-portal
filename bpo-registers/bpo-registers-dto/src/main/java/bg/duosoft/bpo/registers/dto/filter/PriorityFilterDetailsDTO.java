package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.publik.core.dto.nomenclature.CountryDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriorityFilterDetailsDTO {
    private FromToFilterDetailsDTO<LocalDate> priorityDate;
    private CountryDTO priorityCountry;
    private String priorityNumber;
}
