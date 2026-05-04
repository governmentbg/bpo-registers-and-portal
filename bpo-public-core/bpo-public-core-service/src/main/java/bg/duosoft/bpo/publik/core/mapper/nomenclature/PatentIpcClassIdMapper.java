package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentIpcClassId;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RelationshipTypeId;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPatentIpcClassId;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ERelationshipTypeId;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: ggeorgiev
 * Date: 27.08.2024
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class PatentIpcClassIdMapper extends BaseObjectMapper<EPatentIpcClassId, PatentIpcClassId> {

}
