package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LocarnoHeadingDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ELocarnoHeading;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * User: ggeorgiev
 * Date: 14.11.2024
 * Time: 14:17
 */
@Mapper(componentModel = "spring", uses = LocarnoClassMapper.class)
public abstract class LocarnoHeadingMapper extends BaseObjectMapper<ELocarnoHeading, LocarnoHeadingDTO> {
    @AfterMapping
    protected void afterToEntity(@MappingTarget ELocarnoHeading target, LocarnoHeadingDTO entity) {
        if (target.getClasses() != null) {
            target.getClasses().forEach(sc -> {
                sc.setLocarnoHeading(target);
                sc.setHeadingId(target.getId());
                sc.setClassId(sc.getId().split("-")[1]);
            });
        }
    }
}
