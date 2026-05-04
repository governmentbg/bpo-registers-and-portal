package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ViennaClassDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EViennaClass;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.ViennaClassMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.ViennaClassRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.ViennaClassService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 13.02.2024
 * Time: 13:31
 */
@Service
@AllArgsConstructor
public class ViennaClassServiceImpl extends CrudServiceBaseImpl<String, ViennaClassDTO> implements ViennaClassService {

    private final ViennaClassRepository viennaClassRepository;
    private final ViennaClassMapper viennaClassMapper;

    @Override
    public List<ViennaClassDTO> autocompleteViennaClasses(AutocompleteFilterDTO autocompleteFilter) {
        if(autocompleteFilter.getName() != null) {
            List<EViennaClass> foundViennas = viennaClassRepository.findAllWithNameOrIdLike(autocompleteFilter.getName() + "%",
                    "%" + autocompleteFilter.getName() + "%", PageRequest.of(autocompleteFilter.getPage(), autocompleteFilter.getPageSize()));
            return viennaClassMapper.toDtoList(foundViennas);
        }
        return new ArrayList<>();
    }

    @Override
    protected ViennaClassRepository getRepository() {
        return viennaClassRepository;
    }

    @Override
    protected ViennaClassMapper getMapper() {
        return viennaClassMapper;
    }

    @Override
    protected Validator<ViennaClassDTO> getValidator() {
        return null;
    }

    @Override
    public boolean isCacheable() {
        return true;
    }
}
