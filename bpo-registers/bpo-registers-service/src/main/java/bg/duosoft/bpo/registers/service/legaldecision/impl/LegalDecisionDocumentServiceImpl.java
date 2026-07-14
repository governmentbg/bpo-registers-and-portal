package bg.duosoft.bpo.registers.service.legaldecision.impl;

import bg.duosoft.bpo.registers.dto.filter.LegalDecisionFilter;
import bg.duosoft.bpo.registers.dto.legaldecision.LegalDecisionDocumentDTO;
import bg.duosoft.bpo.registers.entity.legaldecision.ELegalDecisionDocument;
import bg.duosoft.bpo.registers.mapper.ipobject.search.LegalDecisionFilterDetailsMapper;
import bg.duosoft.bpo.registers.mapper.legaldecision.LegalDecisionDocumentMapper;
import bg.duosoft.bpo.registers.nonentity.filter.ELegalDecisionDocumentDataFilter;
import bg.duosoft.bpo.registers.repository.legaldecision.LegalDecisionDocumentRepository;
import bg.duosoft.bpo.registers.service.legaldecision.LegalDecisionDocumentService;
import bg.duosoft.bpo.registers.service.search.SearchServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class LegalDecisionDocumentServiceImpl extends SearchServiceBase implements LegalDecisionDocumentService {

    private final LegalDecisionFilterDetailsMapper legalDecisionFilterDetailsMapper;
    private final LegalDecisionDocumentRepository ipLegalDecisionRepository;
    private final LegalDecisionDocumentMapper ipLegalDecisionMapper;

    @Override
    public Page<LegalDecisionDocumentDTO> filterDocuments(LegalDecisionFilter legalDecisionFilter) {
        ELegalDecisionDocumentDataFilter eIpLegalDecisionDataFilter = this.legalDecisionFilterDetailsMapper.toEIpDocumentData(legalDecisionFilter);
        List<ELegalDecisionDocument> eIpLegalDecisionList = ipLegalDecisionRepository.filterLegalDecisions(eIpLegalDecisionDataFilter);
        List<LegalDecisionDocumentDTO> legalDecisionDtoList = ipLegalDecisionMapper.toDtoList(eIpLegalDecisionList);
        Integer legalDecisionsCount = ipLegalDecisionRepository.countDocuments(eIpLegalDecisionDataFilter);
        Pageable pageable = getPage(legalDecisionFilter);
        return new PageImpl(legalDecisionDtoList, pageable, legalDecisionsCount);
    }

    public LegalDecisionDocumentDTO getById(String id) {
        Optional<ELegalDecisionDocument> res = ipLegalDecisionRepository.findById(id);
        if (res.isEmpty()) {
            return null;
        }
        return ipLegalDecisionMapper.toDto(res.get());
    }

    @Override
    public List<LegalDecisionDocumentDTO> getAllByObjectId(String objectId) {
        return ipLegalDecisionMapper.toDtoList(ipLegalDecisionRepository.getAllByObjectId(objectId));
    }
}
