package bg.duosoft.bpo.publik.core.dto.common;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.AttachmentTypeDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:49
 */
@Data
public class AttachmentDTO implements BaseDTO<Integer> {

    private Integer id;
    private String fileName;
    private String contentType;
    private Integer fileSize;
    private String bucketName;
    private String fileLocation;
    private String description;
    private AttachmentTypeDTO attachmentType;
}
