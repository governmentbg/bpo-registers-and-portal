package bg.duosoft.bpo.portal.service.impl;

import bg.duosoft.bpo.common.service.service.impl.CrudServiceBaseImpl;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.portal.dto.PortalText;
import bg.duosoft.bpo.portal.entity.PortalTextEntity;
import bg.duosoft.bpo.portal.mapper.PortalTextMapper;
import bg.duosoft.bpo.portal.repository.PortalTextRepository;
import bg.duosoft.bpo.portal.service.PortalTextService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PortalTextServiceImpl extends CrudServiceBaseImpl<String, PortalText> implements PortalTextService {

    private static final Integer DYNAMIC_CONTENT_ACTIVE = 1;

    private final PortalTextRepository portalTextRepository;
    private final PortalTextMapper portalTextMapper;

    @Override
    public PortalText getActivePortalText() {
        List<PortalTextEntity> portalTextEntityList = this.portalTextRepository
                .getActivePortalText(DYNAMIC_CONTENT_ACTIVE,
                        LocalDateTime.now());
        if (!portalTextEntityList.isEmpty()) {
            return this.portalTextMapper.toDto(portalTextEntityList.getFirst());
        }
        return null;
    }

    @Override
    protected PortalTextRepository getRepository() {
        return this.portalTextRepository;
    }

    @Override
    protected PortalTextMapper getMapper() {
        return this.portalTextMapper;
    }

    @Override
    protected Validator<PortalText> getValidator() {
        return null;
    }

    @Override
    protected boolean isSortable() {
        return true;
    }

    @Override
    protected String getSortColumn() {
        return "id";
    }
}
