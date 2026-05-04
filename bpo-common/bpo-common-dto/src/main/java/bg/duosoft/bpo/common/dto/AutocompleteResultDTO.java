package bg.duosoft.bpo.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 09.02.2024
 * Time: 15:11
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class AutocompleteResultDTO implements BaseDTO<String> {

    private String id;
    private String name;
}
