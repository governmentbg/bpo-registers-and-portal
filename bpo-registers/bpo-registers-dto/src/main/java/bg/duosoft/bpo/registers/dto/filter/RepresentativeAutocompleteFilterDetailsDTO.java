package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.BaseFilterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 14.03.2024
 * Time: 18:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepresentativeAutocompleteFilterDetailsDTO extends BaseFilterDTO {

    private String name;
    private List<String> objectTypes;
}
