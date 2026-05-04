package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.EDrawingViewType;
import bg.duosoft.bpo.publik.core.dto.nomenclature.DrawingViewTypeDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 17:09
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class DrawingViewTypeMapper extends BaseObjectMapper<EDrawingViewType, DrawingViewTypeDTO> {
}
