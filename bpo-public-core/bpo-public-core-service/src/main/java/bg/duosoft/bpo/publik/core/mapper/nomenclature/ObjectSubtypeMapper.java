package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.EObjectSubtype;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ObjectSubtypeDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 12:54
 */
@Mapper(componentModel = "spring", uses = {ObjectTypeMapper.class})
public abstract class ObjectSubtypeMapper extends BaseObjectMapper<EObjectSubtype, ObjectSubtypeDTO> {
}
