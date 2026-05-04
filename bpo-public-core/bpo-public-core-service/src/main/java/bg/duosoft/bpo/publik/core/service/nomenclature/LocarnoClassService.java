package bg.duosoft.bpo.publik.core.service.nomenclature;

import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LocarnoClassDTO;

import java.util.List;

public interface LocarnoClassService extends CrudServiceBase<String, LocarnoClassDTO> {

    List<LocarnoClassDTO> autocompleteLocarnoClasses(AutocompleteFilterDTO autocompleteFilter);
}
