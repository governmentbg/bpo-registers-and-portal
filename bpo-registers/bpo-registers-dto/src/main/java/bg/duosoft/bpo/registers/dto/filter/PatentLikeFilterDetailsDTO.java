package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.TextSearchType;
import bg.duosoft.bpo.publik.core.dto.nomenclature.CountryDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.02.2024
 * Time: 16:40
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatentLikeFilterDetailsDTO extends CommonIpObjectFilterDetailsDTO {
    @JsonProperty("abstract")
    private TextMatchFilterDetailsDTO abstractDetails;
    private IpcFilterDetailsDTO ipcClasses;
    private String ipcCode;
    private CpcFilterDetailsDTO cpcClasses;
    private String cpcCode;
    private String inventor;
    private TextSearchType inventorPersonSearchType;
    private CountryDTO inventorCountry;

}
