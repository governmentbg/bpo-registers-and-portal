package bg.duosoft.bpo.publik.core.service.nomenclature;

import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentCpcClassDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentCpcClassId;

import java.util.List;

public interface PatentCpcClassService extends CrudServiceBase<PatentCpcClassId, PatentCpcClassDTO> {
    List<PatentCpcClassDTO> autocompleteCpc(AutocompleteFilterDTO autocompleteFilter);
}
