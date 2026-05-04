package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.BaseFilterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 09.02.2024
 * Time: 14:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectAutocompleteFilter extends BaseFilterDTO {
    private List<String> objectRange;
    private String filingNumber;
    private String registrationNumber;

}
