package bg.duosoft.bpo.portal.mapper;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.portal.dto.Section;
import bg.duosoft.bpo.portal.entity.PanelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PanelChildMapper.class})
public abstract class PanelMapper extends BaseObjectMapper<PanelEntity, Section> {
}
