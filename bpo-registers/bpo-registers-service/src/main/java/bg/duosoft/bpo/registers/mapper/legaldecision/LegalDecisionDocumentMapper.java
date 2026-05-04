package bg.duosoft.bpo.registers.mapper.legaldecision;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.LegalDecisionGroundTypeMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.LegalDecisionTypeMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.ObjectTypeMapper;
import bg.duosoft.bpo.registers.dto.legaldecision.LegalDecisionDocumentDTO;
import bg.duosoft.bpo.registers.entity.legaldecision.ELegalDecisionDocument;
import bg.duosoft.bpo.publik.core.mapper.common.AttachmentMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        LegalDecisionTypeMapper.class,
        ObjectTypeMapper.class,
        LegalDecisionGroundTypeMapper.class,
        AttachmentMapper.class
})
public abstract class LegalDecisionDocumentMapper extends BaseObjectMapper<ELegalDecisionDocument, LegalDecisionDocumentDTO> {

}
