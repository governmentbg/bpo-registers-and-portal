package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.common.AttachmentDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.DrawingViewTypeDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 17:01
 */
@Data
public class IpSingleDesignDrawingDTO implements BaseDTO<IpSingleDesignDrawingId> {

    private IpSingleDesignDrawingId id;

    private DrawingViewTypeDTO viewType;
    private AttachmentDTO attachment;
    private AttachmentDTO thumbnail;
    private AttachmentDTO dsviewPicture;
}
