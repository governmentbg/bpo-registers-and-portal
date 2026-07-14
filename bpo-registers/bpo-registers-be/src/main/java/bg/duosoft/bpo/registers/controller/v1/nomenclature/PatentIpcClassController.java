package bg.duosoft.bpo.registers.controller.v1.nomenclature;

import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.web.controller.CrudController;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentIpcClassDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentIpcClassId;
import bg.duosoft.bpo.publik.core.service.nomenclature.PatentIpcClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nomenclature/patent-ipc-class")
public class PatentIpcClassController extends CrudController<PatentIpcClassId, PatentIpcClassDTO> {
    private final PatentIpcClassService service;

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    protected PatentIpcClassService getService() {
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
    public List<PatentIpcClassDTO> autocompleteIpc(AutocompleteFilterDTO autocompleteFilter) {
        return service.autocompleteIpc(autocompleteFilter);
    }
}
