package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentCpcClassDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPatentCpcClass;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PatentCpcClassIdMapper.class})
public abstract class PatentCpcClassMapper extends BaseObjectMapper<EPatentCpcClass, PatentCpcClassDTO> {

}
