package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.SearchOperatorType;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LocarnoClassDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocarnoClassesFilterDetailsDTO {

    private List<LocarnoClassDTO> locarnoClasses;
    private SearchOperatorType locarnoClassCodeType;
}
