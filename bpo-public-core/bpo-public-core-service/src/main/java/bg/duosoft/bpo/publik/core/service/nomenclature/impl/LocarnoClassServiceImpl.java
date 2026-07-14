package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LocarnoClassDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ELocarnoClass;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.LocarnoClassMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.LocarnoClassRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.LocarnoClassService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LocarnoClassServiceImpl extends CrudServiceBaseImpl<String, LocarnoClassDTO> implements LocarnoClassService {

    private final LocarnoClassRepository locarnoClassRepository;
    private final LocarnoClassMapper locarnoClassMapper;

    @Override
    public List<LocarnoClassDTO> autocompleteLocarnoClasses(AutocompleteFilterDTO autocompleteFilter) {
        if (autocompleteFilter.getName() != null) {
            List<ELocarnoClass> foundLocarnos = locarnoClassRepository.findAllWithDescriptionOrIdLike(autocompleteFilter.getName() + "%",
                    "%" + autocompleteFilter.getName() + "%", PageRequest.of(autocompleteFilter.getPage(), autocompleteFilter.getPageSize()));
            return locarnoClassMapper.toDtoList(foundLocarnos);
        }
        return new ArrayList<>();
    }

    @Override
    protected LocarnoClassRepository getRepository() {
        return locarnoClassRepository;
    }

    @Override
    protected LocarnoClassMapper getMapper() {
        return locarnoClassMapper;
    }

    @Override
    protected Validator<LocarnoClassDTO> getValidator() {
        return null;
    }

    @Override
    public boolean isCacheable() {
        return true;
    }
}
