package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ObjectSubtypeDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EObjectSubtype;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.ObjectSubtypeMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.ObjectSubTypeRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.ObjectSubtypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectSubtypeServiceImpl extends CrudServiceBaseImpl<String, ObjectSubtypeDTO> implements ObjectSubtypeService {

    private final ObjectSubTypeRepository objectSubTypeRepository;
    private final ObjectSubtypeMapper objectSubtypeMapper;

    @Override
    public List<ObjectSubtypeDTO> selectByObjectTypes(List<String> objectTypes) {
        List<EObjectSubtype> objectSubtypesByTypes = objectSubTypeRepository.selectByObjectTypeIn(objectTypes);
        return objectSubtypeMapper.toDtoList(objectSubtypesByTypes);
    }

    @Override
    protected ObjectSubTypeRepository getRepository() {
        return objectSubTypeRepository;
    }

    @Override
    protected ObjectSubtypeMapper getMapper() {
        return objectSubtypeMapper;
    }

    @Override
    protected Validator<ObjectSubtypeDTO> getValidator() {
        return null;
    }

    @Override
    public boolean isCacheable() {
        return true;
    }

}
