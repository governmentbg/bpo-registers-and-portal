package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.CountryDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.CountryMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.CountryRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * User: ggeorgiev
 * Date: 15.01.2024
 * Time: 13:37
 */
@RequiredArgsConstructor
@Service
public class CountryServiceImpl extends CrudServiceBaseImpl<String, CountryDTO> implements CountryService {
    private final CountryRepository repository;
    private final CountryMapper countryMapper;

    @Override
    protected CountryRepository getRepository() {
        return repository;
    }

    @Override
    protected CountryMapper getMapper() {
        return countryMapper;
    }

    @Override
    protected Validator<CountryDTO> getValidator() {
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
