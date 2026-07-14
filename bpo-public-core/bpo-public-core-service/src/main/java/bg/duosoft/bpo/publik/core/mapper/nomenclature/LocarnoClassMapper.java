package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LocarnoClassDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ELocarnoClass;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 14.11.2024
 * Time: 14:16
 */
@Mapper(componentModel = "spring")
public abstract class LocarnoClassMapper extends BaseObjectMapper<ELocarnoClass, LocarnoClassDTO> {
}
