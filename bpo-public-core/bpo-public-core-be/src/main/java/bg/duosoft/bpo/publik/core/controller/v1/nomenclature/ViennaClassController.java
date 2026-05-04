package bg.duosoft.bpo.publik.core.controller.v1.nomenclature;

import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.common.web.controller.CrudController;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ViennaClassDTO;
import bg.duosoft.bpo.publik.core.service.nomenclature.ViennaClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 13.02.2024
 * Time: 13:37
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nomenclature/viennaclass")
public class ViennaClassController extends CrudController<String, ViennaClassDTO> {

    private final ViennaClassService viennaClassService;

    @GetMapping("/autocomplete")
    public List<ViennaClassDTO> autocompleteViennaClasses(AutocompleteFilterDTO autocompleteFilter){
        return viennaClassService.autocompleteViennaClasses(autocompleteFilter);
    }

    @Override
    protected CrudServiceBase<String, ViennaClassDTO> getService() {
        return viennaClassService;
    }

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    public boolean getAccessIsSecured() {
        return false;
    }

    @Override
    protected Set<ENDPOINT> getSupportedEndpoints() {
        return Set.of(ENDPOINT.LIST);
    }
}
