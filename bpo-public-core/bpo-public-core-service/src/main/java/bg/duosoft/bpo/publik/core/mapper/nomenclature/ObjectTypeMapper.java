package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.EObjectType;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ObjectTypeDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:25
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class ObjectTypeMapper extends BaseObjectMapper<EObjectType, ObjectTypeDTO> {
}
