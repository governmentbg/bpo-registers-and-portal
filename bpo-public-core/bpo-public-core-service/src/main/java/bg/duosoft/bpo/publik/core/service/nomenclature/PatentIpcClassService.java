package bg.duosoft.bpo.publik.core.service.nomenclature;

import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentIpcClassDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentIpcClassId;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 27.08.2024
 */
public interface PatentIpcClassService extends CrudServiceBase<PatentIpcClassId, PatentIpcClassDTO> {
    List<PatentIpcClassDTO> autocompleteIpc(AutocompleteFilterDTO autocompleteFilter);
}
