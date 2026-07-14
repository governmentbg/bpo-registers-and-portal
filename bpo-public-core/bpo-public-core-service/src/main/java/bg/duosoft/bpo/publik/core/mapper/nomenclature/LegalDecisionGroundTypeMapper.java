package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.ELegalDecisionGroundType;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LegalDecisionGroundTypeDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public abstract class LegalDecisionGroundTypeMapper extends BaseObjectMapper<ELegalDecisionGroundType, LegalDecisionGroundTypeDTO> {
}
