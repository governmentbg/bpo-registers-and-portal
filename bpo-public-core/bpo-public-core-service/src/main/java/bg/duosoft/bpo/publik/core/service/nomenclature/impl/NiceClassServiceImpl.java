package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.publik.core.dto.nomenclature.NiceClassDTO;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.NiceClassMapper;
import bg.duosoft.bpo.publik.core.repository.nomenclature.NiceClassRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.NiceClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NiceClassServiceImpl implements NiceClassService {

    public static final Integer GOODS_LAST_NUMBER = 34;
    public static final Integer SERVICES_LAST_NUMBER = 45;

    private final NiceClassRepository niceClassRepository;
    private final NiceClassMapper niceClassMapper;

    @Override
    public List<NiceClassDTO> selectNiceClassRange(Boolean onlyGoods) {
        return this.niceClassMapper
                .toDtoList(this.niceClassRepository
                        .selectNiceClassesRange(onlyGoods ?
                                GOODS_LAST_NUMBER : SERVICES_LAST_NUMBER));
    }
}
