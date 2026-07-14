package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.SearchOperatorType;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ViennaClassDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 14.02.2024
 * Time: 16:03
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViennaClassesFilterDetailsDTO {

    private List<ViennaClassDTO> viennaClasses;
    private SearchOperatorType viennaClassCodeType;
}
