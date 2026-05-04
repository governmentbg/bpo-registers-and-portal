package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.enums.PersonRole;
import bg.duosoft.bpo.registers.dto.ipobject.VwPersonObjectRelationshipDTO;
import bg.duosoft.bpo.registers.entity.ipobject.VwPersonObjectRelationship;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring", uses = {
        VwPersonObjectRelationshipIdMapper.class
})
public abstract class VwPersonObjectRelationshipMapper extends BaseObjectMapper<VwPersonObjectRelationship, VwPersonObjectRelationshipDTO> {

    public abstract VwPersonObjectRelationshipDTO toDto(VwPersonObjectRelationship vwPersonObjectRelationship);

    @AfterMapping
    protected void afterMapping(@MappingTarget VwPersonObjectRelationshipDTO target) {
        if (StringUtils.hasText(target.getId().getObjectId()) && target.getId().getRoleCode().equals(PersonRole.INVENTOR.code())) {
            String[] split = target.getId().getObjectId().split("/");
            if (split.length == 4 && split[1].equals("Д")) {
                target.setRoleDescription("Автор");
                target.setRoleDescriptionEn("Author");
            }
        }
    }
}
