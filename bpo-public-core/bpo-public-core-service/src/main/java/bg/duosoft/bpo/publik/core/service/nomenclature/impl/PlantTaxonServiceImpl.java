package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PlantTaxonDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.PlantTaxonMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.PlantTaxonRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.PlantTaxonService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlantTaxonServiceImpl extends CrudServiceBaseImpl<Integer, PlantTaxonDTO> implements PlantTaxonService {

    private final PlantTaxonRepository plantTaxonRepository;
    private final PlantTaxonMapper plantTaxonMapper;

    @Override
    protected PlantTaxonRepository getRepository() {
        return plantTaxonRepository;
    }

    @Override
    protected PlantTaxonMapper getMapper() {
        return plantTaxonMapper;
    }

    @Override
    protected Validator<PlantTaxonDTO> getValidator() {
        return null;
    }

    @Cacheable(cacheResolver = "crudCacheResolver", key = "'$all-bg-classifications'")
    public List<String> getAllBgClassifications() {
        return plantTaxonRepository.getAllBgClassifications();
    }

    @Cacheable(cacheResolver = "crudCacheResolver", key = "'$all-latin-classifications'")
    public List<String> getAllLatinClassifications() {
        return plantTaxonRepository.getAllLatinClassifications();
    }

    @Override
    public List<PlantTaxonDTO> getAutocompletePlantTaxon(String word, Integer limit) {
        return this.plantTaxonMapper.toDtoList(this.plantTaxonRepository.getAutocompletePlantTaxon(word, limit));
    }

    @Override
    public boolean isCacheable() {
        return true;
    }
}

