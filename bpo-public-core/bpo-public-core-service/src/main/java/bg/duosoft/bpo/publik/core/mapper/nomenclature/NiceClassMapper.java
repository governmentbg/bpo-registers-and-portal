package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.NiceClassDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ENiceClass;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class NiceClassMapper extends BaseObjectMapper<ENiceClass, NiceClassDTO> {
}
