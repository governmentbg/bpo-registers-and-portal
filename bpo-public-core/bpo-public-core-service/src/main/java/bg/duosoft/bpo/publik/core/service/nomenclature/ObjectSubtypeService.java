package bg.duosoft.bpo.publik.core.service.nomenclature;

import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ObjectSubtypeDTO;

import java.util.List;

public interface ObjectSubtypeService extends CrudServiceBase<String, ObjectSubtypeDTO> {
    List<ObjectSubtypeDTO> selectByObjectTypes(List<String> objectTypes);
}
