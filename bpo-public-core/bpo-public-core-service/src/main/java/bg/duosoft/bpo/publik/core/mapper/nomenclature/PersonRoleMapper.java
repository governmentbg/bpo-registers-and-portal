package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.EPersonRole;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PersonRoleDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:21
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class PersonRoleMapper extends BaseObjectMapper<EPersonRole, PersonRoleDTO> {
}
