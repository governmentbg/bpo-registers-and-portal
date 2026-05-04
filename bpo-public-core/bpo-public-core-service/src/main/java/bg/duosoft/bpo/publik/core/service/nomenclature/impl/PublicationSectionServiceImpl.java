package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PublicationSectionDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.PublicationSectionMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.PublicationSectionRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.PublicationSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PublicationSectionServiceImpl extends CrudServiceBaseImpl<Integer, PublicationSectionDTO> implements PublicationSectionService {

    private final PublicationSectionRepository repository;
    private final PublicationSectionMapper mapper;

    @Override
    protected PublicationSectionRepository getRepository() {
        return repository;
    }

    @Override
    protected PublicationSectionMapper getMapper() {
        return mapper;
    }

    @Override
    protected Validator<PublicationSectionDTO> getValidator() {
        return null;
    }

    @Override
    protected boolean isSortable() {
        return true;
    }

    @Override
    protected String getSortColumn() {
        return "nmsection";
    }

    @Override
    public boolean isCacheable() {
        return true;
    }
}

