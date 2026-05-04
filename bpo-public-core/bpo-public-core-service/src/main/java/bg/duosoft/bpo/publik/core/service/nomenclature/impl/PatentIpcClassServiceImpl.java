package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentIpcClassDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentIpcClassId;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPatentIpcClass;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPatentIpcClassId;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.PatentIpcClassIdMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.PatentIpcClassMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.PatentIpcClassRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.PatentIpcClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 27.08.2024
 */
@RequiredArgsConstructor
@Service
public class PatentIpcClassServiceImpl extends CrudServiceBaseImpl<PatentIpcClassId, PatentIpcClassDTO> implements PatentIpcClassService {
    private final PatentIpcClassRepository repository;
    private final PatentIpcClassMapper mapper;
    private final PatentIpcClassIdMapper idMapper;

    @Override
    protected PatentIpcClassRepository getRepository() {
        return repository;
    }

    @Override
    protected PatentIpcClassMapper getMapper() {
        return mapper;
    }

    @Override
    protected Validator<PatentIpcClassDTO> getValidator() {
        return null;
    }

    @Override
    protected EPatentIpcClassId toEntityId(PatentIpcClassId patentIpcClassId) {
        return idMapper.toEntity(patentIpcClassId);
    }

    @Override
    public boolean isCacheable() {
        return true;
    }

    @Override
    public List<PatentIpcClassDTO> autocompleteIpc(AutocompleteFilterDTO autocompleteFilter) {
        if (autocompleteFilter.getName() != null) {
            List<EPatentIpcClass> foundIpcs = repository.findAllWithDescriptionOrIdLike("%" + autocompleteFilter.getName().replaceAll("[^a-zA-Z0-9]", "") + "%", PageRequest.of(autocompleteFilter.getPage(), autocompleteFilter.getPageSize()));
            return mapper.toDtoList(foundIpcs);
        }
        return new ArrayList<>();
    }
}
