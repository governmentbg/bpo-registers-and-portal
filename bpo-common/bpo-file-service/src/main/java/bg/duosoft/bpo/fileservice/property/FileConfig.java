package bg.duosoft.bpo.fileservice.property;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.06.2022
 * Time: 17:18
 */
@Data
public class FileConfig {

    private Integer maxSize;
    private String allowedTypes;
}
