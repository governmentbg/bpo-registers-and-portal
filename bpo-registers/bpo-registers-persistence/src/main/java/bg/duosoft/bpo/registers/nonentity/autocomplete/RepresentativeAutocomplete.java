package bg.duosoft.bpo.registers.nonentity.autocomplete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.03.2024
 * Time: 18:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepresentativeAutocomplete {

    private Integer id;
    private String name;
    private String agentCode;
    private String representativeType;
    private String representativeTypeName;
    private String representativeTypeNameEn;
}
