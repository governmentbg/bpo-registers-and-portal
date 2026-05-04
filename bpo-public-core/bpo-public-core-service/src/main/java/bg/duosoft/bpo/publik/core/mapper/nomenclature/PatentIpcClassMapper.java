package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentIpcClassDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RelationshipTypeDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPatentIpcClass;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ERelationshipType;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: ggeorgiev
 * Date: 27.08.2024
 */
@Mapper(componentModel = "spring", uses = {PatentIpcClassIdMapper.class})
public abstract class PatentIpcClassMapper extends BaseObjectMapper<EPatentIpcClass, PatentIpcClassDTO> {

}
