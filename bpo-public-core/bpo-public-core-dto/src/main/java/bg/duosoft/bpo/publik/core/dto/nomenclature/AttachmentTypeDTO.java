package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:50
 */
@Data
public class AttachmentTypeDTO implements BaseDTO<String> {

    private String id;
    private String name;
    private String nameEn;
    private String backofficeCode;
    private List<AttachmentCategoryDTO> categories;
}
