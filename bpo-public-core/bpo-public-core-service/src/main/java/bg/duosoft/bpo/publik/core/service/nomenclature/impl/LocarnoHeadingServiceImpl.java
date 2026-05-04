package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LocarnoHeadingDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.LocarnoHeadingMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.LocarnoHeadingRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.LocarnoHeadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: ggeorgiev
 * Date: 14.11.2024
 * Time: 14:14
 */
@Service
@RequiredArgsConstructor
@Transactional
public class LocarnoHeadingServiceImpl extends CrudServiceBaseImpl<String, LocarnoHeadingDTO> implements LocarnoHeadingService {
    private final LocarnoHeadingRepository repository;
    private final LocarnoHeadingMapper mapper;
    @Override
    protected LocarnoHeadingRepository getRepository() {
        return repository;
    }

    @Override
    protected LocarnoHeadingMapper getMapper() {
        return mapper;
    }

    @Override
    protected Validator<LocarnoHeadingDTO> getValidator() {
        return null;
    }
}
