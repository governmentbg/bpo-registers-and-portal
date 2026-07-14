package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.EDsViewStatusMap;
import bg.duosoft.bpo.publik.core.dto.nomenclature.DsViewStratusMapDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 12:58
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class DsViewStatusMapMapper extends BaseObjectMapper<EDsViewStatusMap, DsViewStratusMapDTO> {
}
