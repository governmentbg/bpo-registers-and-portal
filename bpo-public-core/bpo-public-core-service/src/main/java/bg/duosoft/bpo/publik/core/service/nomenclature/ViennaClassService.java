package bg.duosoft.bpo.publik.core.service.nomenclature;

import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ViennaClassDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 13.02.2024
 * Time: 13:31
 */
public interface ViennaClassService extends CrudServiceBase<String, ViennaClassDTO> {

    List<ViennaClassDTO> autocompleteViennaClasses(AutocompleteFilterDTO autocompleteFilter);
}
