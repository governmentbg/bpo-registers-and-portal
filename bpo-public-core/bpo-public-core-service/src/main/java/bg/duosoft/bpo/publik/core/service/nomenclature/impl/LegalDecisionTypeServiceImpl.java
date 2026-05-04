package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LegalDecisionTypeDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.LegalDecisionTypeMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.LegalDecisionTypeRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.LegalDecisionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LegalDecisionTypeServiceImpl extends CrudServiceBaseImpl<Integer, LegalDecisionTypeDTO> implements LegalDecisionTypeService {

    private final LegalDecisionTypeRepository repository;
    private final LegalDecisionTypeMapper mapper;

    @Override
    protected LegalDecisionTypeRepository getRepository() {
        return repository;
    }

    @Override
    protected LegalDecisionTypeMapper getMapper() {
        return mapper;
    }

    @Override
    protected Validator<LegalDecisionTypeDTO> getValidator() {
        return null;
    }

    @Override
    protected boolean isSortable() {
        return true;
    }

    @Override
    protected String getSortColumn() {
        return "name";
    }

    @Override
    public boolean isCacheable() {
        return true;
    }
}
