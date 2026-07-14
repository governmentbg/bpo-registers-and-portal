package bg.duosoft.bpo.publik.core.service.nomenclature;

import bg.duosoft.bpo.publik.core.dto.nomenclature.NiceClassDTO;

import java.util.List;

public interface NiceClassService {
    List<NiceClassDTO> selectNiceClassRange(Boolean onlyGoods);
}
