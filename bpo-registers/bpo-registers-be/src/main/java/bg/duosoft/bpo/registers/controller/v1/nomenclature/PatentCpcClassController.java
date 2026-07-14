package bg.duosoft.bpo.registers.controller.v1.nomenclature;

import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.web.controller.CrudController;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentCpcClassDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentCpcClassId;
import bg.duosoft.bpo.publik.core.service.nomenclature.PatentCpcClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nomenclature/patent-cpc-class")
public class PatentCpcClassController extends CrudController<PatentCpcClassId, PatentCpcClassDTO> {
    private final PatentCpcClassService service;

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    protected PatentCpcClassService getService() {
        return service;
    }

    @Override
    protected Set<ENDPOINT> getSupportedEndpoints() {
        return Set.of(ENDPOINT.LIST, ENDPOINT.GET);
    }

    @Override
    public boolean getAccessIsSecured() {
        return false;
    }

    @GetMapping("/autocomplete")
    public List<PatentCpcClassDTO> autocompleteCpc(AutocompleteFilterDTO autocompleteFilter) {
        return service.autocompleteCpc(autocompleteFilter);
    }
}
