package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.common.AttachmentDTO;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:08
 */
@Data
public class IpMarkAttachmentDTO implements BaseDTO<IpMarkAttachmentId> {

    private IpMarkAttachmentId id;

    private AttachmentDTO attachment;
    private AttachmentDTO thumbnail;
    private AttachmentDTO tmviewPicture;
    private String colorDescription;
    private List<IpMarkAttachmentViennaClassDTO> viennaClasses;
}
