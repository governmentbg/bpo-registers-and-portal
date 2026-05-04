package bg.duosoft.bpo.publik.core.mapper.common;

import bg.duosoft.bpo.publik.core.dto.common.AttachmentDTO;
import bg.duosoft.bpo.publik.core.entity.common.EAttachment;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.AttachmentTypeMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:28
 */
@Mapper(componentModel = "spring", uses = {
        AttachmentTypeMapper.class
})
public abstract class AttachmentMapper extends BaseObjectMapper<EAttachment, AttachmentDTO> {
}
