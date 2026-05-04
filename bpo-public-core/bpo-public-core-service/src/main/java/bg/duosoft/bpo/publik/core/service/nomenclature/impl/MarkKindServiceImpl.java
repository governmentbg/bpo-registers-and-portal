package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.publik.core.dto.nomenclature.MarkKindDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.MarkKindMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.MarkKindRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.MarkKindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.02.2024
 * Time: 13:48
 */
@RequiredArgsConstructor
@Service
public class MarkKindServiceImpl extends CrudServiceBaseImpl<String, MarkKindDTO> implements MarkKindService {

    private final MarkKindRepository markKindRepository;
    private final MarkKindMapper markKindMapper;

    @Override
    protected MarkKindRepository getRepository() {
        return markKindRepository;
    }

    @Override
    protected MarkKindMapper getMapper() {
        return markKindMapper;
    }

    @Override
    protected Validator<MarkKindDTO> getValidator() {
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

