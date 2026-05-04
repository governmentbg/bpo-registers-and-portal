package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentCpcClassDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentCpcClassId;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPatentCpcClass;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPatentCpcClassId;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.PatentCpcClassIdMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.PatentCpcClassMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.PatentCpcClassRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.PatentCpcClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: akehayov
 * Date: 28.11.2024
 */
@RequiredArgsConstructor
@Service
public class PatentCpcClassServiceImpl extends CrudServiceBaseImpl<PatentCpcClassId, PatentCpcClassDTO> implements PatentCpcClassService {
    private final PatentCpcClassRepository repository;
    private final PatentCpcClassMapper mapper;
    private final PatentCpcClassIdMapper idMapper;

    @Override
    protected PatentCpcClassRepository getRepository() {
        return repository;
    }

    @Override
    protected PatentCpcClassMapper getMapper() {
        return mapper;
    }

    @Override
    protected Validator<PatentCpcClassDTO> getValidator() {
        return null;
    }

    @Override
    protected EPatentCpcClassId toEntityId(PatentCpcClassId patentCpcClassId) {
        return idMapper.toEntity(patentCpcClassId);
    }

    @Override
    public boolean isCacheable() {
        return true;
    }

    @Override
    public List<PatentCpcClassDTO> autocompleteCpc(AutocompleteFilterDTO autocompleteFilter) {
        if (autocompleteFilter.getName() != null) {
            List<EPatentCpcClass> foundCpcs = repository.findAllWithDescriptionOrIdLike("%" + autocompleteFilter.getName().replaceAll("[^a-zA-Z0-9]", "") + "%", PageRequest.of(autocompleteFilter.getPage(), autocompleteFilter.getPageSize()));
            return mapper.toDtoList(foundCpcs);
        }
        return new ArrayList<>();
    }
}
