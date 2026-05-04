package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ObjectTypeDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.ObjectTypeMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.ObjectTypeRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.ObjectTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ObjectTypeServiceImpl extends CrudServiceBaseImpl<String, ObjectTypeDTO> implements ObjectTypeService {
    private final ObjectTypeRepository repository;
    private final ObjectTypeMapper objectTypeMapper;

    @Override
    protected ObjectTypeRepository getRepository() {
        return repository;
    }

    @Override
    protected ObjectTypeMapper getMapper() {
        return objectTypeMapper;
    }

    @Override
    protected Validator<ObjectTypeDTO> getValidator() {
        return null;
    }

    @Override
    protected boolean isSortable() {
        return true;
    }

    @Override
    protected String getSortColumn() {
        return "description";
    }

    @Override
    public boolean isCacheable() {
        return true;
    }
}
