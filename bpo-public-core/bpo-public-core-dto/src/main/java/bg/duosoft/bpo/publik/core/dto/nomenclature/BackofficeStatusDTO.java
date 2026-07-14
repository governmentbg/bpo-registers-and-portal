package bg.duosoft.bpo.publik.core.dto.nomenclature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.02.2024
 * Time: 18:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackofficeStatusDTO {
    private String code;
    private String status;
}
