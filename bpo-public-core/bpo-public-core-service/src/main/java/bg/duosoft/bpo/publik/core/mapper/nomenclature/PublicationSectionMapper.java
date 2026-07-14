package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PublicationSectionDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPublicationSection;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.02.2024
 * Time: 11:51
 */
@Mapper(componentModel = "spring")
public abstract class PublicationSectionMapper extends BaseObjectMapper<EPublicationSection, PublicationSectionDTO> {
}
