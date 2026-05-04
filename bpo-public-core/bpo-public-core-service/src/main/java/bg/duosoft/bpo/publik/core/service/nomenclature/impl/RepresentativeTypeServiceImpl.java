package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RepresentativeTypeDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.RepresentativeTypeMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.RepresentativeTypeRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.RepresentativeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 14.03.2024
 * Time: 17:08
 */
@Service
@RequiredArgsConstructor
public class RepresentativeTypeServiceImpl extends CrudServiceBaseImpl<String, RepresentativeTypeDTO>  implements RepresentativeTypeService {

    private final RepresentativeTypeRepository representativeTypeRepository;
    private final RepresentativeTypeMapper representativeTypeMapper;

    @Override
    protected RepresentativeTypeRepository getRepository() {
        return representativeTypeRepository;
    }

    @Override
    protected RepresentativeTypeMapper getMapper() {
        return representativeTypeMapper;
    }

    @Override
    protected Validator<RepresentativeTypeDTO> getValidator() {
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
