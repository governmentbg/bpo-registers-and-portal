package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.EStatusMap;
import bg.duosoft.bpo.publik.core.dto.nomenclature.StatusMapDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 12:55
 */
@Mapper(componentModel = "spring", uses = {
        TmViewStatusMapMapper.class,
        EpoPatentStatusMapMapper.class,
        DsViewStatusMapMapper.class
})
public abstract class StatusMapMapper extends BaseObjectMapper<EStatusMap, StatusMapDTO> {
}
