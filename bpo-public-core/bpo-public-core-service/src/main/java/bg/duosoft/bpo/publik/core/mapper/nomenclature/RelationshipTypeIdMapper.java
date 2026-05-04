package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RelationshipTypeId;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ERelationshipTypeId;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:02
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class RelationshipTypeIdMapper extends BaseObjectMapper<ERelationshipTypeId, RelationshipTypeId> {

}
