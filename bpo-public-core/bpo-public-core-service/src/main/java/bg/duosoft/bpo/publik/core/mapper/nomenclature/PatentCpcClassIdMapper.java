package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentCpcClassId;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPatentCpcClassId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public abstract class PatentCpcClassIdMapper extends BaseObjectMapper<EPatentCpcClassId, PatentCpcClassId> {

}
