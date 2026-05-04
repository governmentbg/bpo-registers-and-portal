package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.02.2024
 * Time: 11:49
 */
@Data
public class PublicationSectionDTO implements BaseDTO<Integer> {

    private Integer id;
    private String nmsection;
    private String nmsectionEn;
    private String publicationCode;
}
