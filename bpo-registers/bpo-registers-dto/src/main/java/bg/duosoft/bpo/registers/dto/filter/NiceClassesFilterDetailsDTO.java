package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.SearchOperatorType;
import bg.duosoft.bpo.common.dto.filter.TextSearchType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NiceClassesFilterDetailsDTO {
    private List<String> niceClassCodes;
    private SearchOperatorType niceClassCodeType;
    private TextMatchFilterDetailsDTO specification;
}
