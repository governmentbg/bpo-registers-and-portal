package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.common.service.mapper.IntegerToBooleanMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ViennaClassDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EViennaClass;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 13.02.2024
 * Time: 13:30
 */
@Mapper(componentModel = "spring", uses = {
        IntegerToBooleanMapper.class
})
public abstract class ViennaClassMapper extends BaseObjectMapper<EViennaClass, ViennaClassDTO> {
}
