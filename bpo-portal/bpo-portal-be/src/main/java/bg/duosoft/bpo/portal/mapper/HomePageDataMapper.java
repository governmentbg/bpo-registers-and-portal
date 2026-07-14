package bg.duosoft.bpo.portal.mapper;

import bg.duosoft.bpo.portal.dto.HomePageData;
import bg.duosoft.bpo.portal.entity.PanelEntity;
import bg.duosoft.bpo.portal.entity.PortalTextEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PanelMapper.class})
public abstract class HomePageDataMapper {

    @Mapping(target = "id", source = "portalTextEntity.id")
    @Mapping(target = "descriptionBg", source = "portalTextEntity.descriptionBg")
    @Mapping(target = "descriptionEn", source = "portalTextEntity.descriptionEn")
    @Mapping(target = "sections", source = "panelEntityList")
    public abstract HomePageData toDto(PortalTextEntity portalTextEntity, List<PanelEntity> panelEntityList);
}
