package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RelationshipTypeDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ERelationshipType;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:02
 */
@Mapper(componentModel = "spring", uses = {RelationshipTypeIdMapper.class})
public abstract class RelationshipTypeMapper extends BaseObjectMapper<ERelationshipType, RelationshipTypeDTO> {

}
