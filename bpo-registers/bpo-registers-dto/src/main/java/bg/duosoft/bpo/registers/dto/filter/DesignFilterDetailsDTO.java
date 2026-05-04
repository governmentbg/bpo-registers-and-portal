package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.TextSearchType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesignFilterDetailsDTO extends PatentLikeFilterDetailsDTO {
    private String singleDesignName;
    private TextSearchType singleDesignNameSearchType;
    private String singleDesignVerbalElement;
    private TextSearchType singleDesignVerbalElementSearchType;
    private LocarnoClassesFilterDetailsDTO locarnoClasses;
}
