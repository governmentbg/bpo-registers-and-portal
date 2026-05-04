package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AttachmentCategoryDTO implements BaseDTO<String> {
    public AttachmentCategoryDTO(String id) {
        this.id = id;
    }
    private String id;
    private String name;
    private String nameEn;

}