package bg.duosoft.bpo.publik.core.controller.v1.nomenclature;

import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.common.web.controller.CrudController;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LocarnoClassDTO;
import bg.duosoft.bpo.publik.core.service.nomenclature.LocarnoClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nomenclature/locarnoclass")
public class LocarnoClassController extends CrudController<String, LocarnoClassDTO> {

    private final LocarnoClassService locarnoClassService;

    @GetMapping("/autocomplete")
    public List<LocarnoClassDTO> autocompleteLocarnoClasses(AutocompleteFilterDTO autocompleteFilter) {
        return locarnoClassService.autocompleteLocarnoClasses(autocompleteFilter);
    }

    @Override
    protected CrudServiceBase<String, LocarnoClassDTO> getService() {
        return locarnoClassService;
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
