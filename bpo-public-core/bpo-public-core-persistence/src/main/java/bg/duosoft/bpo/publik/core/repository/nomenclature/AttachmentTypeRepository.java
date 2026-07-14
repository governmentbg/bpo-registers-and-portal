package bg.duosoft.bpo.publik.core.repository.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.EAttachmentType;
import bg.duosoft.bpo.common.repository.BaseRepository;

public interface AttachmentTypeRepository extends BaseRepository<String, EAttachmentType> {
    public EAttachmentType getByBackofficeCode(String backofficeCode);
}

