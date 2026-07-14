package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.common.AttachmentMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpSingleDesignDrawingDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpSingleDesignDrawing;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.DrawingViewTypeMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 17:12
 */
@Mapper(componentModel = "spring", uses = {
        DrawingViewTypeMapper.class,
        AttachmentMapper.class,
        IpSingleDesignDrawingIdMapper.class
})
public abstract class IpSingleDesignDrawingMapper extends BaseObjectMapper<EIpSingleDesignDrawing, IpSingleDesignDrawingDTO> {

    public abstract IpSingleDesignDrawingDTO toDto(EIpSingleDesignDrawing eIpSingleDesignDrawing);
}
