package bg.duosoft.bpo.portal.mapper;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.portal.dto.AdminPanel;
import bg.duosoft.bpo.portal.entity.AdminPanelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AdminPanelMapper extends BaseObjectMapper<AdminPanelEntity, AdminPanel> {
    @Mapping(target = "accessRoles", expression = "java(initAccessRoles(entity.getAccessRoles()))")
    public abstract AdminPanel toDto(AdminPanelEntity entity);

    @Mapping(target = "accessRoles", expression = "java(initAccessRoles(dto.getAccessRoles()))")
    public abstract AdminPanelEntity toEntity(AdminPanel dto);

    protected List<String> initAccessRoles(String accessRoles) {
        if (ObjectUtils.isEmpty(accessRoles)) {
            return null;
        }
        return Arrays.stream(accessRoles.split(","))
                .map(String::trim)
                .toList();
    }

    protected String initAccessRoles(List<String> accessRoles) {
        if (ObjectUtils.isEmpty(accessRoles)) {
            return null;
        }
        return String.join(",", accessRoles);
    }
}
