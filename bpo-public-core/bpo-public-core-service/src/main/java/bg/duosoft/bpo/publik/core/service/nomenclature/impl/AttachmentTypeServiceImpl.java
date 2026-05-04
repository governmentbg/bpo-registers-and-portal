package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.AttachmentTypeDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.AttachmentTypeMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.AttachmentTypeRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.AttachmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AttachmentTypeServiceImpl extends CrudServiceBaseImpl<String, AttachmentTypeDTO> implements AttachmentTypeService {
    private final AttachmentTypeRepository repository;
    private final AttachmentTypeMapper mapper;

    @Override
    protected AttachmentTypeRepository getRepository() {
        return repository;
    }

    @Override
    protected AttachmentTypeMapper getMapper() {
        return mapper;
    }

    @Override
    protected Validator<AttachmentTypeDTO> getValidator() {
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
