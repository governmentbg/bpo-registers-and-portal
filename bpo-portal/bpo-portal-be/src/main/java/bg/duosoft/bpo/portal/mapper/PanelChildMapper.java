package bg.duosoft.bpo.portal.mapper;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.portal.dto.SectionChild;
import bg.duosoft.bpo.portal.entity.PanelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PanelChildMapper extends BaseObjectMapper<PanelEntity, SectionChild> {
    @Mapping(target = "titleBg", source = "labelBg")
    @Mapping(target = "titleEn", source = "labelEn")
    @Mapping(target = "action", source = "url")
    @Mapping(target = "actionTarget", expression = "java(initActionTarget(entity.getOpenInNewTab()))")
    public abstract SectionChild toDto(PanelEntity entity);

    protected String initActionTarget(Integer openInNewTab) {
        return openInNewTab == 1 ? "_blank" : "_self";
    }
}
