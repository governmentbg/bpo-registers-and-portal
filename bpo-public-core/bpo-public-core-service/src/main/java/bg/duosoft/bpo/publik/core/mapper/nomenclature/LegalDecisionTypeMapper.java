package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.ELegalDecisionType;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LegalDecisionTypeDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public abstract class LegalDecisionTypeMapper extends BaseObjectMapper<ELegalDecisionType, LegalDecisionTypeDTO> {
}
