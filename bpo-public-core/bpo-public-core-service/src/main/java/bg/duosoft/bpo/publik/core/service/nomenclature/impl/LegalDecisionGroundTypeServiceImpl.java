package bg.duosoft.bpo.publik.core.service.nomenclature.impl;


import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LegalDecisionGroundTypeDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.LegalDecisionGroundTypeMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.LegalDecisionGroundTypeRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.LegalDecisionGroundTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LegalDecisionGroundTypeServiceImpl extends CrudServiceBaseImpl<Integer, LegalDecisionGroundTypeDTO> implements LegalDecisionGroundTypeService {

    private final LegalDecisionGroundTypeRepository repository;
    private final LegalDecisionGroundTypeMapper mapper;

    @Override
    protected LegalDecisionGroundTypeRepository getRepository() {
        return repository;
    }

    @Override
    protected LegalDecisionGroundTypeMapper getMapper() {
        return mapper;
    }

    @Override
    protected Validator<LegalDecisionGroundTypeDTO> getValidator() {
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
