package bg.duosoft.bpo.registers.mapper.recordal;

import bg.duosoft.bpo.publik.core.entity.nomenclature.ERecordalType;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RecordalTypeDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 15:35
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class RecordalTypeMapper extends BaseObjectMapper<ERecordalType, RecordalTypeDTO> {
}
