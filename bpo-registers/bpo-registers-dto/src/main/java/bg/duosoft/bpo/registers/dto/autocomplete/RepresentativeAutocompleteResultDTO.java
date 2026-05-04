package bg.duosoft.bpo.registers.dto.autocomplete;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.03.2024
 * Time: 14:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepresentativeAutocompleteResultDTO implements BaseDTO<Integer> {

    private Integer id;
    private String name;
    private String agentCode;
    private String representativeType;
    private String representativeTypeName;
    private String representativeTypeNameEn;
}
